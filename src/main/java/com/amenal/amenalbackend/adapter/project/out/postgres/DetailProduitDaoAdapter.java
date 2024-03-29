package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.DetailProduitEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.DetailProduitRepository;
import com.amenal.amenalbackend.application.project.domain.DetailProduit;
import com.amenal.amenalbackend.application.project.port.out.DetailProduitDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class DetailProduitDaoAdapter implements DetailProduitDao {

	@Autowired
	private DetailProduitRepository detailProduitRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public DetailProduit findDetailProduitById(Integer id) {
		DetailProduitEntity detailProduitEntity = detailProduitRepository.findById(id).get();
		DetailProduit detailProduit = modelMapper.map(detailProduitEntity, DetailProduit.class);
		return detailProduit;
	}

	@Override
	public List<DetailProduit> findAllDetailProduits() {
		List<DetailProduitEntity> detailProduitEntities = detailProduitRepository.findAll();
		return detailProduitEntities.stream().map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class))
				.collect(Collectors.toList());
	}

	@Override
	public DetailProduit saveDetailProduit(DetailProduit detailProduit) throws DuplicateElementException {
		try {
			// if there is a detailProduit with the same designation in the same avenant:
			List<DetailProduitEntity> sameDetailProduitEntities = detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(
					detailProduit.getTache().getId(), detailProduit.getReference());
			List<DetailProduit> sameDetailProduits = sameDetailProduitEntities.stream()
					.map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class)).collect(Collectors.toList());
			if (!sameDetailProduits.isEmpty()) {
				throw new DuplicateElementException("Charge existe deja");
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		DetailProduitEntity detailProduitEntity = modelMapper.map(detailProduit, DetailProduitEntity.class);
		DetailProduitEntity savedEntity = detailProduitRepository.save(detailProduitEntity);
		return modelMapper.map(savedEntity, DetailProduit.class);
	}

	@Override
	public DetailProduit updateDetailProduit(DetailProduit detailProduit) throws DuplicateElementException {
		DetailProduitEntity existingEntity = detailProduitRepository.findById(detailProduit.getId()).orElseThrow();

		try {
			// if there is a detailProduit with the same designation in the same avenant:
			if(!detailProduit.getReference().equals(existingEntity.getReference())) {
				List<DetailProduitEntity> sameDetailProduitEntities = detailProduitRepository.getDetailProduitsByTacheIdAndDesignation(
						detailProduit.getTache().getId(), detailProduit.getReference());
				List<DetailProduit> sameDetailProduits = sameDetailProduitEntities.stream()
						.map(detailProduitEntity -> modelMapper.map(detailProduitEntity, DetailProduit.class)).collect(Collectors.toList());
				if (!sameDetailProduits.isEmpty()) {
					throw new DuplicateElementException("Charge existe deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.print(e);
		}
		// if not:
		// Use ModelMapper to map non-null properties from DetailProduit to existingEntity
		modelMapper.map(detailProduit, existingEntity);

		DetailProduitEntity updatedEntity = detailProduitRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, DetailProduit.class);
	}

	@Override
	public void deleteDetailProduit(Integer id) {
		// Check if DetailProduit with the given ID exists
		DetailProduitEntity detailProduitEntity = detailProduitRepository.findById(id).orElseThrow();

		// Delete the entity
		detailProduitRepository.delete(detailProduitEntity);
	}

}

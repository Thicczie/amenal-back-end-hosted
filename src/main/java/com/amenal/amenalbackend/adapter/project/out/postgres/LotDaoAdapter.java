package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.LotEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.LotRepository;
import com.amenal.amenalbackend.application.project.domain.Lot;
import com.amenal.amenalbackend.application.project.port.out.LotDao;
import com.amenal.amenalbackend.infrastructure.exception.DuplicateElementException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class LotDaoAdapter implements LotDao {

	@Autowired
	private LotRepository lotRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Lot findLotById(Integer id) {
		LotEntity lotEntity = lotRepository.findById(id).get();
		Lot lot = modelMapper.map(lotEntity, Lot.class);
		return lot;
	}

	@Override
	public List<Lot> findAllLots() {
		List<LotEntity> lotEntities = lotRepository.findAll();
		return lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Lot> getLotsByAvenantId(Integer id) {
		List<LotEntity> lotEntities = lotRepository.getLotsByAvenantId(id);
		return lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Lot> getLotsByProjectId(Integer id) {
		List<LotEntity> lotEntities = lotRepository.getLotsByProjectId(id);
		return lotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
				.collect(Collectors.toList());
	}

	@Override
	public Lot saveLot(Lot lot) {
		try {
			// if there is a lot with the same designation in the same project:
			List<LotEntity> sameLotEntities = lotRepository.getLotsByProjectIdAndDesignation(lot.getProject().getId(),
					lot.getDesignation());
			List<Lot> sameLots = sameLotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
					.collect(Collectors.toList());
			if (!sameLots.isEmpty()) {
				return sameLots.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// if not:
		LotEntity lotEntity = modelMapper.map(lot, LotEntity.class);
		LotEntity savedEntity = lotRepository.save(lotEntity);
		return modelMapper.map(savedEntity, Lot.class);
	}

	@Override
	public Lot updateLot(Lot lot) throws DuplicateElementException {
		LotEntity existingEntity = lotRepository.findById(lot.getId()).orElseThrow();

		try {
			// if there is a lot with the same designation in the same project:
			if (!lot.getDesignation().equals(existingEntity.getDesignation())) {
				List<LotEntity> sameLotEntities = lotRepository
						.getLotsByProjectIdAndDesignation(lot.getProject().getId(), lot.getDesignation());
				List<Lot> sameLots = sameLotEntities.stream().map(lotEntity -> modelMapper.map(lotEntity, Lot.class))
						.collect(Collectors.toList());
				if (!sameLots.isEmpty()) {
					throw new DuplicateElementException("Lot Existe Deja");
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		// Use ModelMapper to map non-null properties from Lot to existingEntity
		modelMapper.map(lot, existingEntity);

		LotEntity updatedEntity = lotRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Lot.class);
	}

	@Override
	public void deleteLot(Integer id) {
		// Check if Lot with the given ID exists
		LotEntity lotEntity = lotRepository.findById(id).orElseThrow();

		// Delete the entity
		lotRepository.delete(lotEntity);
	}

}

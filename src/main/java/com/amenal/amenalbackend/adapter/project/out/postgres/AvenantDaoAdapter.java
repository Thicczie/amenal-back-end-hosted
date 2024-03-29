package com.amenal.amenalbackend.adapter.project.out.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amenal.amenalbackend.adapter.project.out.postgres.entities.AvenantEntity;
import com.amenal.amenalbackend.adapter.project.out.postgres.repositories.AvenantRepository;
import com.amenal.amenalbackend.application.project.domain.Avenant;
import com.amenal.amenalbackend.application.project.port.out.AvenantDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class AvenantDaoAdapter implements AvenantDao {

	@Autowired
	private AvenantRepository avenantRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Avenant findAvenantById(Integer id) {
		AvenantEntity avenantEntity = avenantRepository.findById(id).get();
		Avenant avenant = modelMapper.map(avenantEntity, Avenant.class);
		return avenant;
	}

	@Override
	public List<Avenant> findAllAvenants() {
		List<AvenantEntity> avenantEntities = avenantRepository.findAll();
		return avenantEntities.stream().map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class))
				.collect(Collectors.toList());
	}

	@Override
	public Avenant saveAvenant(Avenant avenant) {
		AvenantEntity avenantEntity = modelMapper.map(avenant, AvenantEntity.class);
		AvenantEntity savedEntity = avenantRepository.save(avenantEntity);
		return modelMapper.map(savedEntity, Avenant.class);
	}

	@Override
	public Avenant updateAvenant(Avenant avenant) {
		AvenantEntity existingEntity = avenantRepository.findById(avenant.getId()).orElseThrow();

		// Use ModelMapper to map non-null properties from Avenant to existingEntity
		modelMapper.map(avenant, existingEntity);

		AvenantEntity updatedEntity = avenantRepository.save(existingEntity);
		return modelMapper.map(updatedEntity, Avenant.class);
	}

	@Override
	public void deleteAvenant(Integer id) {
		// Check if Avenant with the given ID exists
		AvenantEntity avenantEntity = avenantRepository.findById(id).orElseThrow();

		// Delete the entity
		avenantRepository.delete(avenantEntity);
	}

	@Override
	public List<Avenant> getAvenantsByProjectId(Integer id) {
		List<AvenantEntity> avenantEntities = avenantRepository.getAvenantsByProjectId(id);
		return avenantEntities.stream().map(avenantEntity -> modelMapper.map(avenantEntity, Avenant.class))
				.collect(Collectors.toList());
	}

}

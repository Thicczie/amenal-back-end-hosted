package com.amenal.amenalbackend.application.project.dto;

import java.time.LocalDate;

public class TacheTableDto {
	private Integer id;
	private Integer ordre;
	private String produit;
	private String lot;
	private String activite;
	private Boolean c;
	private String upb;
	private Double qpm;
	private Double ppm;
	private Double mpm;
	private Double qpb;
	private Double ppb;
	private Double mpb;
	private Double mrg;
	private Double prcRg;
	private LocalDate ddb;
	private Integer dlb;
	private LocalDate dfb;
	private Integer produitId;
	private Integer lotId;

	public TacheTableDto(Integer id, Integer ordre, String produit, String lot, String activite, Boolean c, String upb, Double qpm,
			Double ppm, Double mpm, Double qpb, Double ppb, Double mpb, Double mrg, Double prcRg, LocalDate ddb,
			Integer dlb, LocalDate dfb, Integer produitId, Integer lotId) {
		super();
		this.id = id;
		this.ordre = ordre;
		this.produit = produit;
		this.lot = lot;
		this.activite = activite;
		this.c = c;
		this.upb = upb;
		this.qpm = qpm;
		this.ppm = ppm;
		this.mpm = mpm;
		this.qpb = qpb;
		this.ppb = ppb;
		this.mpb = mpb;
		this.mrg = mrg;
		this.prcRg = prcRg;
		this.ddb = ddb;
		this.dlb = dlb;
		this.dfb = dfb;
		this.produitId = produitId;
		this.lotId = lotId;
	}

	public TacheTableDto() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public Boolean getC() {
		return c;
	}

	public void setC(Boolean c) {
		this.c = c;
	}

	public String getUpb() {
		return upb;
	}

	public void setUpb(String upb) {
		this.upb = upb;
	}

	public Double getQpm() {
		return qpm;
	}

	public void setQpm(Double qpm) {
		this.qpm = qpm;
	}

	public Double getPpm() {
		return ppm;
	}

	public void setPpm(Double ppm) {
		this.ppm = ppm;
	}

	public Double getMpm() {
		return mpm;
	}

	public void setMpm(Double mpm) {
		this.mpm = mpm;
	}

	public Double getQpb() {
		return qpb;
	}

	public void setQpb(Double qpb) {
		this.qpb = qpb;
	}

	public Double getPpb() {
		return ppb;
	}

	public void setPpb(Double ppb) {
		this.ppb = ppb;
	}

	public Double getMpb() {
		return mpb;
	}

	public void setMpb(Double mpb) {
		this.mpb = mpb;
	}

	public Double getMrg() {
		return mrg;
	}

	public void setMrg(Double mrg) {
		this.mrg = mrg;
	}

	public Double getPrcRg() {
		return prcRg;
	}

	public void setPrcRg(Double prcRg) {
		this.prcRg = prcRg;
	}

	public LocalDate getDdb() {
		return ddb;
	}

	public void setDdb(LocalDate ddb) {
		this.ddb = ddb;
	}

	public Integer getDlb() {
		return dlb;
	}

	public void setDlb(Integer dlb) {
		this.dlb = dlb;
	}

	public LocalDate getDfb() {
		return dfb;
	}

	public void setDfb(LocalDate dfb) {
		this.dfb = dfb;
	}

	public Integer getProduitId() {
		return produitId;
	}

	public void setProduitId(Integer produitId) {
		this.produitId = produitId;
	}

	public Integer getLotId() {
		return lotId;
	}

	public void setLotId(Integer lotId) {
		this.lotId = lotId;
	}

}

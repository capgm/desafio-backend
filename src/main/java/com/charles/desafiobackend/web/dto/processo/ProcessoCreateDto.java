package com.charles.desafiobackend.web.dto.processo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProcessoCreateDto {

    @NotBlank
    @Size(min=20, max=20, message="O npu deve ter 20 caracteres")
    @Pattern(regexp="\\d+", message="Apenas números são permitidos")
    private String npu;

    @NotBlank
    private String municipio;

    @NotBlank
    @Size(min=2, max=2, message="O estado deve ter 2 caracteres")
    private String uf;

    @NotNull
    private byte[] documentoPdf;

    // Getters and Setters
    public String getNpu() {
        return npu;
    }

    public void setNpu(String npu) {
        this.npu = npu;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public byte[] getDocumentoPdf() {
        return documentoPdf;
    }

    public void setDocumentoPdf(byte[] documentoPdf) {
        this.documentoPdf = documentoPdf;
    }

	public ProcessoCreateDto(
			@NotBlank @Size(min = 20, max = 20, message = "O npu deve ter 20 caracteres") @Pattern(regexp = "\\d+", message = "Apenas números são permitidos") String npu,
			@NotBlank String municipio,
			@NotBlank @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres") String uf,
			@NotNull byte[] documentoPdf) {
		super();
		this.npu = npu;
		this.municipio = municipio;
		this.uf = uf;
		this.documentoPdf = documentoPdf;
	}
    
	public ProcessoCreateDto() {
		super();
	}
}

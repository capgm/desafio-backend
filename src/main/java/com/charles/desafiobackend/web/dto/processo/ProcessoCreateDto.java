package com.charles.desafiobackend.web.dto.processo;

import jakarta.validation.constraints.NotNull;

public class ProcessoCreateDto {

    @NotNull
    private String npu;

    @NotNull
    private String municipio;

    @NotNull
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
}

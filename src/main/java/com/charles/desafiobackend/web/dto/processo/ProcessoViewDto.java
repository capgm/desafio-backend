package com.charles.desafiobackend.web.dto.processo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ProcessoViewDto {

    @NotBlank
    private String npu;

    private LocalDateTime dataCadastro;

    private LocalDateTime dataVisualizacao;

    @NotBlank
    private String municipio;

    @NotBlank
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDateTime dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
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

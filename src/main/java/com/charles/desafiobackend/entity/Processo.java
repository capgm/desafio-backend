package com.charles.desafiobackend.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "processos")
@EntityListeners(AuditingEntityListener.class)
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String npu;   
    
    @CreatedDate
	@Column(name = "dataCadastro")
	private LocalDateTime dataCadastro;
	
	@LastModifiedDate
	@Column(name = "dataVisualizacao")
	private LocalDateTime dataVisualizacao;    

    @NotBlank
    private String municipio;

    @NotBlank
    private String uf;

    @NotNull
    @Lob
    private byte[] documentoPdf;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


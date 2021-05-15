package com.rodolfoguerra.cursomc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodolfoguerra.cursomc.model.enums.ClientType;
import com.rodolfoguerra.cursomc.model.enums.Profile;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Data
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique=true)
    private String email;

    private String cpfOrCnpj;

    private Integer type;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "Phones")
    private Set<String> phones = new HashSet<>();

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "Profiles")
    private Set<Integer> profiles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    private String imageUrl;

    public Client(Long id, String name, String email, String cpfOrCnpj, ClientType type, String password) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = type == null ? null : type.getCode();
        this.password = password;
        addProfile(Profile.CLIENT);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCode());
    }

    public ClientType getType() {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type) {
        this.type = type.getCode();
    }
}
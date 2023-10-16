package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    // TODO Singleton: Injetar os componentes do Spring @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    // TODO Strategy: Implementar os métodos definidos na Interface.
    // TODO Facede: Abstrair integrações com subsistemas, provendo uma interface simples.








    @Override
    public Iterable<Cliente> buscarTodos(){
        //FIXME buscar todos os clientes
        return clienteRepository.findAll();
    }

    @Override
    public void inserir(Cliente cliente){
        salvarClienteComCEP(cliente);
    }
    @Override
    public Cliente buscarPorId(Long id){
        //FIXME buscar Cliente por Id
        //return null;
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void atualizar(Long id, Cliente cliente){
        //FIXME: buscar Cliente por ID, caso exista
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            //FIXME: verificar se o Endereço do Cliente já existe(pelo CEP)
            //FIXME: Caso não exista, integrar com o ViaCEP e persistir o retorno
            //FIXME: Alterar Cliente, vinculando o Endereço (novo ou existente)
            salvarClienteComCEP(cliente);
        }
    }

    @Override
    public void deletar(Long id){
        //FIXME: Deletar Cliente porId
        clienteRepository.deleteById(id);
    }

    public void salvarClienteComCEP(Cliente cliente){
        //FIXME: verificar se o Endereço do Cliente já existe(pelo CEP)
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()-> {
            //FIXME: Caso não exista, integrar com o ViaCEP e persistir o retorno
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        //FIXME: Inserir Cliente, vinculando o Endereço (novo ou existente)
        clienteRepository.save(cliente);
    }
}

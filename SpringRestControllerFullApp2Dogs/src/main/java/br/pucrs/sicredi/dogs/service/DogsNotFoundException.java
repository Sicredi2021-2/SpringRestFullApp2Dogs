package br.pucrs.sicredi.dogs.service;

public class DogsNotFoundException extends RuntimeException {
	public DogsNotFoundException(String msg) {
		super(msg);
	}

}

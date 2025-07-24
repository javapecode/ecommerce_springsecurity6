package com.ecommerceapp.servic;

import java.util.List;

import com.ecommerceapp.entity.State;

public interface StateService {
	State createState(State state);

	State getStateById(int id);

	List<State> getAllStates();

	State updateState(int id, State state);

	void deleteState(int id);

	List<State> searchStatesByName(String name);

}

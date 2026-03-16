package app.service;

import app.repository.testrepo;
// delete this class later 
public class testService {

    private testrepo repo = new testrepo();

    public String getMessage(){

        String dbMessage = repo.getMessageFromDB();

        return "Service processed: " + dbMessage;
    }

}
package DAO;

public interface DAOInterfazDeJavaNoGrafica {

    boolean create(String rutaFichero);
    boolean read(String rutaFichero);
    boolean update(String rutaFichero);
    boolean delete(String fichero);
    
}

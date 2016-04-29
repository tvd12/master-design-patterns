package com.tvd12.designparttern.bridge;

import java.io.File;

public class DataBridgeExample {

	public static void main(String[] args) {
		// this program needs a persistence framework
		// at runtime an implementor is chosen between file system
		// implementation and
		// database implememtor , depending on existence of databse drivers
		PersistenceImplementor implementor = null;
		if(databaseDriverExists()) {
			implementor = new DatabasePersistenceImplementor();
		}
		else {
			implementor = new FileSystemPersistenceImplementor();
		}
		
		Persistence persistenceAPI = new PersistenceImp(implementor);
		
		Object object = persistenceAPI.findById("30051992");
		
		//do something with object
		persistenceAPI.persist(object);
		
		persistenceAPI = new PersistenceImp(new DatabasePersistenceImplementor());
		
		persistenceAPI.deleteById("123456");
	}
	
	private static boolean databaseDriverExists() {
		
		return false;
	}

}

interface Persistence {

	public String persist(Object object);

	public Object findById(String objectId);

	public void deleteById(String id);

}

interface PersistenceImplementor {

	public long saveObject(Object object);

	public void deleteObject(long objectId);

	public Object getObject(long objectId);

}

class PersistenceImp implements Persistence {

	public PersistenceImp(PersistenceImplementor imp) {
		this.mImplementor = imp;
	}

	public static PersistenceImp create(PersistenceImplementor imp) {
		PersistenceImp pRet = new PersistenceImp(imp);

		return pRet;
	}

	@Override
	public String persist(Object object) {
		return Long.toString(mImplementor.saveObject(object));
	}

	@Override
	public Object findById(String objectId) {
		return mImplementor.getObject(Long.parseLong(objectId));
	}

	@Override
	public void deleteById(String id) {
		mImplementor.deleteObject(Long.parseLong(id));
	}

	private PersistenceImplementor mImplementor;
}

class FileSystemPersistenceImplementor implements PersistenceImplementor {

	@Override
	public long saveObject(Object object) {

		long fileId = System.currentTimeMillis();

		// open file
		File file = getFile(fileId);

		writeObjectToFile(file, object);

		return fileId;
	}

	@Override
	public void deleteObject(long objectId) {
		File file = getFile(objectId);

		file.delete();
	}

	@Override
	public Object getObject(long objectId) {
		File file = getFile(objectId);

		return readObjectFromFile(file);
	}

	public Object readObjectFromFile(File file) {
		System.out.println("read from file: " + file.getPath());

		return null;
	}

	public void writeObjectToFile(File file, Object object) {
		System.out.println("write object " + object.toString() + " to file " + file.getPath());
	}

	private File getFile(long id) {
		File file = new File(File.separator + PERSISTENCE_FOLDER + File.separator + Long.toString(id));

		return file;
	}

	private static final String PERSISTENCE_FOLDER = "persistence";
}

class DatabasePersistenceImplementor implements PersistenceImplementor {

	public static DatabasePersistenceImplementor create() {
		DatabasePersistenceImplementor pRet = new DatabasePersistenceImplementor();

		return pRet;
	}

	@Override
	public long saveObject(Object object) {
		System.out.println("saveObject: " + object);

		return 0;
	}

	@Override
	public void deleteObject(long objectId) {
		System.out.println("deleteObject: " + objectId);
	}

	@Override
	public Object getObject(long objectId) {
		System.out.println("getObject: " + objectId);

		return null;
	}

}
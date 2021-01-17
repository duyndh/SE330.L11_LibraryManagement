package data.Repositories;

import data.DAOs.BaseDAO;
import data.Models.BaseModel;

abstract class BaseRepository<T extends BaseModel> {
    protected BaseDAO<T> modelDAO;

    public BaseRepository(BaseDAO<T> modelDAO) {
        this.modelDAO = modelDAO;
    }

    public T create(T model) {
        return modelDAO.create(model);
    }

    public T read(int id) {
        return modelDAO.read(id);
    }

    public T update(T model) {
        return modelDAO.update(model);
    }

    public boolean delete(int id) {
        return modelDAO.delete(id);
    }
}
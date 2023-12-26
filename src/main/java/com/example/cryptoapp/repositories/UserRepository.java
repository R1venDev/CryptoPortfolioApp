package com.example.cryptoapp.repositories;

import com.example.cryptoapp.models.User;

import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;


public class UserRepository implements IRepository<User> {
    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

    @Override
    public void save(User user) {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch(Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getClass().getName() + "occured: \n" + ex.getMessage());
        }
    }

    @Override
    public User findById(Long id) {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            transaction.commit();
            return user;
        }
        catch(Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getClass().getName() + "occured: \n" + ex.getMessage());
            return null;
        }
        finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public List<User> findAll() {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("FROM com.example.cryptoapp.models.User", User.class).list();
            transaction.commit();
            return users;
        }
        catch(Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getClass().getName() + "occured: \n" + ex.getMessage());
            return new ArrayList<User>();
        }
        finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void update(User user) throws NullPointerException {
        if(user == null) {
            throw new NullPointerException("User to update cannot be null.");
        }

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
        catch(Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getClass().getName() + "occured: \n" + ex.getMessage());
        }
        finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }

    @Override
    public void delete(Long id) throws NullPointerException {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if(user != null) {
                session.delete(user);
                transaction.commit();
            }
            else {
                transaction.rollback();
                throw new NullPointerException("User to delete wasn't found.");
            }
        }
        catch(Exception ex) {
            if(transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getClass().getName() + "occured: \n" + ex.getMessage());
        }
        finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}



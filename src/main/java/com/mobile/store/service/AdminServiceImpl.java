package com.mobile.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.store.dao.AdminRepository;

import com.mobile.store.dto.Admin;
import com.mobile.store.exception.AdminException;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;

	/**
	* Method:                        - registerAdmin
	* Description:                   - Register Admin in the database
	* @Param Admin                   -  Pass Admin details  
	* @return String                 - Success,if Admin registered successfully,otherwise it throws AdminException.
	* @throws AdminException :       - It is raised due to  Admin already found in the database.
	* Created By                     - Bhumika Mulani
	* Created Date                   -
	*/
	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		Integer adminId = admin.getAdminId();
		Optional<Admin> tempAdmin = this.adminRepository.findById(adminId);
		if (tempAdmin.isPresent()) {
			throw new AdminException("Admin already exists!");
		}
		return adminRepository.save(admin);
	}

	/**
	* Method:                       -  deleteAdmin
	* Description:                  -  Delete Admin from the database
	* @Param Integer                -  Admin Id
	* @Param String                 -  Adminpassword     
	* @return String                -  Success,if admin delete successfully,otherwise it throws AdminException.
	* @throws AdminException :      -  It is raised due to  Customer not found or old password not correct.
	* Created By                    -  Bhumika Mulani
	* Created Date
	*/
	@Override
	public String deleteAdmin(Integer adminId, String adminPassword) throws AdminException {
		String isDeleted;
		Optional<Admin> admin = this.adminRepository.findById(adminId);
		if (admin.isEmpty()) {
			throw new AdminException("Admin does not exist for id " + adminId);
		}
		Admin newAdmin = admin.get();
			if (!(newAdmin.getAdminPassword().equals(adminPassword))) {
				throw new AdminException("Incorrect Password");
			}
			this.adminRepository.deleteById(adminId);
			isDeleted = "Successful";
		return isDeleted;
	}

	/**
	* Method:                     -    updateAdmin
	* Description:                -    Update admin in the database
	* @Param Admin                -    Pass Admindetails  
	* @return String              -    Success,if admin update successfully,otherwise it throws AdminException.
	* @throws AdminException :    -    It is raised due to  Admin not found.
	* Created By                  -    Bhumika Mulani
	* Created Date                -    16-August-2022
	*/
	@Override
	public Admin updateAdmin(Admin admin) throws AdminException {

		Optional<Admin> foundAdmin = this.adminRepository.findById(admin.getAdminId());
		if (foundAdmin.isEmpty()) {
			throw new AdminException("Admin doesnot exists for id " + admin.getAdminId());
		}
		return this.adminRepository.save(admin);
	}

	/**
	* Method:                       -  getAdmin
	* Description:                  -  Fetch admin from the database.  
	* @return List                  -  Present if admin found,otherwise it throws AdminException.
	* @throws AdminException :      -  It is raised due to  Admin not found.
	* Created By                    -  Bhumika Mulani
	* Created Date                  -  16-August-2022
	*/
	@Override
	public List<Admin> getAdmin() throws AdminException {
		List<Admin> admin = this.adminRepository.findAll();
		if (admin.isEmpty())
			throw new AdminException("admin not found");
		return this.adminRepository.findAll();
	}

	/**
	* Method:                       -  login
	* Description:                  -  Login Admin with the help of admin id and password
	* @Param Integer                -  Admin Id
	* @Param  String                -  Admin password  
	* @return String                -  Success,if Admin login successfully,otherwise it throws AdminException.
	* @throws AdminException :      -  It is raised due to  Admin not found or password not match.
	* Created By                    -  Bhumika Mulani
	* Created Date
	*/
	@Override
	public Boolean login(Integer adminId, String adminPassword) throws AdminException {
		boolean isLogedin = false;
		Optional<Admin> foundAdmin = this.adminRepository.findById(adminId);
		if (foundAdmin.isEmpty()) {
			throw new AdminException("Admin doesnot exists for id " + adminId);
		}
		if (!foundAdmin.get().getAdminPassword().equals(adminPassword)) {
			throw new AdminException("Incorrect password");
		}
		if (foundAdmin.get().getAdminPassword().equals(adminPassword)) {
			isLogedin = true;
		}
		return isLogedin;
	}

}
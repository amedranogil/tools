/**
 * 	Copyright 2013 SINTEF, http://www.sintef.no
 * 	
 * 	See the NOTICE file distributed with this work for additional 
 * 	information regarding copyright ownership
 * 	
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 * 	
 * 	  http://www.apache.org/licenses/LICENSE-2.0
 * 	
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 */
package org.universaal.tools.modelling.servicemodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.universaal.tools.modelling.servicemodel.ServiceInterface;
import org.universaal.tools.modelling.servicemodel.ServiceModelPackage;
import org.universaal.tools.modelling.servicemodel.ServiceOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.universaal.tools.modelling.servicemodel.impl.ServiceInterfaceImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.universaal.tools.modelling.servicemodel.impl.ServiceInterfaceImpl#getService <em>Service</em>}</li>
 *   <li>{@link org.universaal.tools.modelling.servicemodel.impl.ServiceInterfaceImpl#getOntology <em>Ontology</em>}</li>
 *   <li>{@link org.universaal.tools.modelling.servicemodel.impl.ServiceInterfaceImpl#getPackageName <em>Package Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceInterfaceImpl extends NamedElementImpl implements ServiceInterface {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<ServiceOperation> operations;

	/**
	 * The cached value of the '{@link #getService() <em>Service</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getService()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Class service;

	/**
	 * The cached value of the '{@link #getOntology() <em>Ontology</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOntology()
	 * @generated
	 * @ordered
	 */
	protected org.eclipse.uml2.uml.Package ontology;

	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ServiceModelPackage.Literals.SERVICE_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ServiceOperation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentEList<ServiceOperation>(ServiceOperation.class, this, ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class getService() {
		if (service != null && service.eIsProxy()) {
			InternalEObject oldService = (InternalEObject)service;
			service = (org.eclipse.uml2.uml.Class)eResolveProxy(oldService);
			if (service != oldService) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServiceModelPackage.SERVICE_INTERFACE__SERVICE, oldService, service));
			}
		}
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Class basicGetService() {
		return service;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setService(org.eclipse.uml2.uml.Class newService) {
		org.eclipse.uml2.uml.Class oldService = service;
		service = newService;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServiceModelPackage.SERVICE_INTERFACE__SERVICE, oldService, service));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package getOntology() {
		if (ontology != null && ontology.eIsProxy()) {
			InternalEObject oldOntology = (InternalEObject)ontology;
			ontology = (org.eclipse.uml2.uml.Package)eResolveProxy(oldOntology);
			if (ontology != oldOntology) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY, oldOntology, ontology));
			}
		}
		return ontology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.uml2.uml.Package basicGetOntology() {
		return ontology;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOntology(org.eclipse.uml2.uml.Package newOntology) {
		org.eclipse.uml2.uml.Package oldOntology = ontology;
		ontology = newOntology;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY, oldOntology, ontology));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ServiceModelPackage.SERVICE_INTERFACE__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS:
				return getOperations();
			case ServiceModelPackage.SERVICE_INTERFACE__SERVICE:
				if (resolve) return getService();
				return basicGetService();
			case ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY:
				if (resolve) return getOntology();
				return basicGetOntology();
			case ServiceModelPackage.SERVICE_INTERFACE__PACKAGE_NAME:
				return getPackageName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends ServiceOperation>)newValue);
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__SERVICE:
				setService((org.eclipse.uml2.uml.Class)newValue);
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY:
				setOntology((org.eclipse.uml2.uml.Package)newValue);
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS:
				getOperations().clear();
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__SERVICE:
				setService((org.eclipse.uml2.uml.Class)null);
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY:
				setOntology((org.eclipse.uml2.uml.Package)null);
				return;
			case ServiceModelPackage.SERVICE_INTERFACE__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ServiceModelPackage.SERVICE_INTERFACE__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case ServiceModelPackage.SERVICE_INTERFACE__SERVICE:
				return service != null;
			case ServiceModelPackage.SERVICE_INTERFACE__ONTOLOGY:
				return ontology != null;
			case ServiceModelPackage.SERVICE_INTERFACE__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (packageName: ");
		result.append(packageName);
		result.append(')');
		return result.toString();
	}

} //ServiceInterfaceImpl

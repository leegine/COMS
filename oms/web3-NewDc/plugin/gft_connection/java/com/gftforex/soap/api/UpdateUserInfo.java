head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.12.16;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UpdateUserInfo.java;

1.1
date	2011.03.15.02.45.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UpdateUserInfo.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:UpdateUserInfo


public  class UpdateUserInfo 
  
  implements java.io.Serializable
{

  public UpdateUserInfo() {}

  public UpdateUserInfo(com.gftforex.soap.api.UserPersonalInfo p_userPersonalInfo
,     java.lang.String[] p_groupsToRemoveFrom
,     java.lang.String[] p_groupsToAddTo
,     java.lang.String p_password
,     com.gftforex.soap.api.UpdateAccountInfo[] p_accounts
,     java.util.Calendar p_expireDate
,     boolean p_isDisabled
,     long[] p_profilesToAdd
,     long[] p_profilesToRemove) 
  {
     this.userPersonalInfo = p_userPersonalInfo;
     this.__isset_userPersonalInfo = true;

     this.groupsToRemoveFrom = p_groupsToRemoveFrom;
     this.__isset_groupsToRemoveFrom = true;

     this.groupsToAddTo = p_groupsToAddTo;
     this.__isset_groupsToAddTo = true;

     this.password = p_password;
     this.__isset_password = true;

     this.accounts = p_accounts;
     this.__isset_accounts = true;

     this.expireDate = p_expireDate;
     this.__isset_expireDate = true;

     this.isDisabled = p_isDisabled;
     this.__isset_isDisabled = true;

     this.profilesToAdd = p_profilesToAdd;
     this.__isset_profilesToAdd = true;

     this.profilesToRemove = p_profilesToRemove;
     this.__isset_profilesToRemove = true;


  }




  
  private com.gftforex.soap.api.UserPersonalInfo userPersonalInfo ;

  /**
  <br>  Derived from UserPersonalInfo.

  <br>  schema name = ['http://soap-api.gftforex.com']:UserPersonalInfo
  <br>  schema type = ['http://soap-api.gftforex.com']:UserPersonalInfo
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.UserPersonalInfo getUserPersonalInfo() {
    return userPersonalInfo;
  }

  public void setUserPersonalInfo(com.gftforex.soap.api.UserPersonalInfo v) {
    
    this.userPersonalInfo = v;
    this.__isset_userPersonalInfo = true;

  }

  private boolean __isset_userPersonalInfo;
  public boolean _isSetUserPersonalInfo() {
    return this.__isset_userPersonalInfo;
  }
  public void _unsetUserPersonalInfo() {
    this.__isset_userPersonalInfo = false;
  }




  
  private java.lang.String[] groupsToRemoveFrom ;

  /**
  <br>  Derived from GroupsToRemoveFrom.

  <br>  schema name = ['http://soap-api.gftforex.com']:GroupsToRemoveFrom
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateUserInfo__groupsToRemoveFrom__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getGroupsToRemoveFrom() {
    return groupsToRemoveFrom;
  }

  public void setGroupsToRemoveFrom(java.lang.String[] v) {
    
    this.groupsToRemoveFrom = v;
    this.__isset_groupsToRemoveFrom = true;

  }

  private boolean __isset_groupsToRemoveFrom;
  public boolean _isSetGroupsToRemoveFrom() {
    return this.__isset_groupsToRemoveFrom;
  }
  public void _unsetGroupsToRemoveFrom() {
    this.__isset_groupsToRemoveFrom = false;
  }




  
  private java.lang.String[] groupsToAddTo ;

  /**
  <br>  Derived from GroupsToAddTo.

  <br>  schema name = ['http://soap-api.gftforex.com']:GroupsToAddTo
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateUserInfo__groupsToAddTo__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getGroupsToAddTo() {
    return groupsToAddTo;
  }

  public void setGroupsToAddTo(java.lang.String[] v) {
    
    this.groupsToAddTo = v;
    this.__isset_groupsToAddTo = true;

  }

  private boolean __isset_groupsToAddTo;
  public boolean _isSetGroupsToAddTo() {
    return this.__isset_groupsToAddTo;
  }
  public void _unsetGroupsToAddTo() {
    this.__isset_groupsToAddTo = false;
  }




  
  private java.lang.String password ;

  /**
  <br>  Derived from Password.

  <br>  schema name = ['http://soap-api.gftforex.com']:Password
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getPassword() {
    return password;
  }

  public void setPassword(java.lang.String v) {
    
    this.password = v;
    this.__isset_password = true;

  }

  private boolean __isset_password;
  public boolean _isSetPassword() {
    return this.__isset_password;
  }
  public void _unsetPassword() {
    this.__isset_password = false;
  }




  
  private com.gftforex.soap.api.UpdateAccountInfo[] accounts ;

  /**
  <br>  Derived from Accounts.

  <br>  schema name = ['http://soap-api.gftforex.com']:Accounts
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateUserInfo__accounts__ArrayAnonType
  <br>  schema formQualified = true
  */
  public com.gftforex.soap.api.UpdateAccountInfo[] getAccounts() {
    return accounts;
  }

  public void setAccounts(com.gftforex.soap.api.UpdateAccountInfo[] v) {
    
    this.accounts = v;
    this.__isset_accounts = true;

  }

  private boolean __isset_accounts;
  public boolean _isSetAccounts() {
    return this.__isset_accounts;
  }
  public void _unsetAccounts() {
    this.__isset_accounts = false;
  }




  
  private java.util.Calendar expireDate ;

  /**
  <br>  Derived from ExpireDate.

  <br>  schema name = ['http://soap-api.gftforex.com']:ExpireDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:date
  <br>  schema formQualified = true
  */
  public java.util.Calendar getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(java.util.Calendar v) {
    
    this.expireDate = v;
    this.__isset_expireDate = true;

  }

  private boolean __isset_expireDate;
  public boolean _isSetExpireDate() {
    return this.__isset_expireDate;
  }
  public void _unsetExpireDate() {
    this.__isset_expireDate = false;
  }




  
  private boolean isDisabled ;

  /**
  <br>  Derived from IsDisabled.

  <br>  schema name = ['http://soap-api.gftforex.com']:IsDisabled
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getIsDisabled() {
    return isDisabled;
  }

  public void setIsDisabled(boolean v) {
    
    this.isDisabled = v;
    this.__isset_isDisabled = true;

  }

  private boolean __isset_isDisabled;
  public boolean _isSetIsDisabled() {
    return this.__isset_isDisabled;
  }
  public void _unsetIsDisabled() {
    this.__isset_isDisabled = false;
  }




  
  private long[] profilesToAdd ;

  /**
  <br>  Derived from ProfilesToAdd.

  <br>  schema name = ['http://soap-api.gftforex.com']:ProfilesToAdd
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateUserInfo__profilesToAdd__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfilesToAdd() {
    return profilesToAdd;
  }

  public void setProfilesToAdd(long[] v) {
    
    this.profilesToAdd = v;
    this.__isset_profilesToAdd = true;

  }

  private boolean __isset_profilesToAdd;
  public boolean _isSetProfilesToAdd() {
    return this.__isset_profilesToAdd;
  }
  public void _unsetProfilesToAdd() {
    this.__isset_profilesToAdd = false;
  }




  
  private long[] profilesToRemove ;

  /**
  <br>  Derived from ProfilesToRemove.

  <br>  schema name = ['http://soap-api.gftforex.com']:ProfilesToRemove
  <br>  schema type = ['http://soap-api.gftforex.com']:UpdateUserInfo__profilesToRemove__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfilesToRemove() {
    return profilesToRemove;
  }

  public void setProfilesToRemove(long[] v) {
    
    this.profilesToRemove = v;
    this.__isset_profilesToRemove = true;

  }

  private boolean __isset_profilesToRemove;
  public boolean _isSetProfilesToRemove() {
    return this.__isset_profilesToRemove;
  }
  public void _unsetProfilesToRemove() {
    this.__isset_profilesToRemove = false;
  }









  public java.lang.String toString() {
  return "UpdateUserInfo" + "{"
             + " userPersonalInfo=<" + getUserPersonalInfo() + ">"
             + " groupsToRemoveFrom=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getGroupsToRemoveFrom() ) + ">"
             + " groupsToAddTo=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getGroupsToAddTo() ) + ">"
             + " password=<" + getPassword() + ">"
             + " accounts=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getAccounts() ) + ">"
             + " expireDate=<" + getExpireDate() + ">"
             + " isDisabled=<" + getIsDisabled() + ">"
             + " profilesToAdd=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfilesToAdd() ) + ">"
             + " profilesToRemove=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfilesToRemove() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof UpdateUserInfo) {
      
      UpdateUserInfo __obj__ =  (UpdateUserInfo) __other__;


      return true
            && (isDisabled == __obj__.getIsDisabled())
            && (userPersonalInfo==null ? __obj__.getUserPersonalInfo()==null : userPersonalInfo.equals(__obj__.getUserPersonalInfo()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(groupsToRemoveFrom, __obj__.getGroupsToRemoveFrom())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(groupsToAddTo, __obj__.getGroupsToAddTo())
            && (password==null ? __obj__.getPassword()==null : password.equals(__obj__.getPassword()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(accounts, __obj__.getAccounts())
            && (expireDate==null ? __obj__.getExpireDate()==null : expireDate.equals(__obj__.getExpireDate()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profilesToAdd, __obj__.getProfilesToAdd())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profilesToRemove, __obj__.getProfilesToRemove())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (userPersonalInfo==null ? 0 : userPersonalInfo.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (groupsToRemoveFrom==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(groupsToRemoveFrom)) ;
    __hash__result__ = 37*__hash__result__ + (groupsToAddTo==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(groupsToAddTo)) ;
    __hash__result__ = 37*__hash__result__ + (password==null ? 0 : password.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (accounts==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(accounts)) ;
    __hash__result__ = 37*__hash__result__ + (expireDate==null ? 0 : expireDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (isDisabled ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (profilesToAdd==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profilesToAdd)) ;
    __hash__result__ = 37*__hash__result__ + (profilesToRemove==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profilesToRemove)) ;
    

    return __hash__result__;
  }

}


@


1.1
log
@*** empty log message ***
@
text
@@


head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.08.11;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	UserSystemInfo.java;

1.1
date	2011.03.15.02.45.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	UserSystemInfo.java;


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

// original type: ['http://soap-api.gftforex.com']:UserSystemInfo


public  class UserSystemInfo 
  
  implements java.io.Serializable
{

  public UserSystemInfo() {}

  public UserSystemInfo(java.lang.String p_login
,     java.lang.String p_password
,     java.lang.String[] p_groups
,     long[] p_profiles
,     java.util.Calendar p_expireDate
,     boolean p_isDisabled) 
  {
     this.login = p_login;
     this.__isset_login = true;

     this.password = p_password;
     this.__isset_password = true;

     this.groups = p_groups;
     this.__isset_groups = true;

     this.profiles = p_profiles;
     this.__isset_profiles = true;

     this.expireDate = p_expireDate;
     this.__isset_expireDate = true;

     this.isDisabled = p_isDisabled;
     this.__isset_isDisabled = true;


  }




  
  private java.lang.String login ;

  /**
  <br>  Derived from Login.

  <br>  schema name = ['http://soap-api.gftforex.com']:Login
  <br>  schema type = ['http://soap-api.gftforex.com']:String32
  <br>  schema formQualified = true
  */
  public java.lang.String getLogin() {
    return login;
  }

  public void setLogin(java.lang.String v) {
    
    this.login = v;
    this.__isset_login = true;

  }

  private boolean __isset_login;
  public boolean _isSetLogin() {
    return this.__isset_login;
  }
  public void _unsetLogin() {
    this.__isset_login = false;
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




  
  private java.lang.String[] groups ;

  /**
  <br>  Derived from Groups.

  <br>  schema name = ['http://soap-api.gftforex.com']:Groups
  <br>  schema type = ['http://soap-api.gftforex.com']:UserSystemInfo__groups__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getGroups() {
    return groups;
  }

  public void setGroups(java.lang.String[] v) {
    
    this.groups = v;
    this.__isset_groups = true;

  }

  private boolean __isset_groups;
  public boolean _isSetGroups() {
    return this.__isset_groups;
  }
  public void _unsetGroups() {
    this.__isset_groups = false;
  }




  
  private long[] profiles ;

  /**
  <br>  Derived from Profiles.

  <br>  schema name = ['http://soap-api.gftforex.com']:Profiles
  <br>  schema type = ['http://soap-api.gftforex.com']:UserSystemInfo__profiles__ArrayAnonType
  <br>  schema formQualified = true
  */
  public long[] getProfiles() {
    return profiles;
  }

  public void setProfiles(long[] v) {
    
    this.profiles = v;
    this.__isset_profiles = true;

  }

  private boolean __isset_profiles;
  public boolean _isSetProfiles() {
    return this.__isset_profiles;
  }
  public void _unsetProfiles() {
    this.__isset_profiles = false;
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









  public java.lang.String toString() {
  return "UserSystemInfo" + "{"
             + " login=<" + getLogin() + ">"
             + " password=<" + getPassword() + ">"
             + " groups=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getGroups() ) + ">"
             + " profiles=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getProfiles() ) + ">"
             + " expireDate=<" + getExpireDate() + ">"
             + " isDisabled=<" + getIsDisabled() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof UserSystemInfo) {
      
      UserSystemInfo __obj__ =  (UserSystemInfo) __other__;


      return true
            && (isDisabled == __obj__.getIsDisabled())
            && (login==null ? __obj__.getLogin()==null : login.equals(__obj__.getLogin()))
            && (password==null ? __obj__.getPassword()==null : password.equals(__obj__.getPassword()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(groups, __obj__.getGroups())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(profiles, __obj__.getProfiles())
            && (expireDate==null ? __obj__.getExpireDate()==null : expireDate.equals(__obj__.getExpireDate()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (login==null ? 0 : login.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (password==null ? 0 : password.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (groups==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(groups)) ;
    __hash__result__ = 37*__hash__result__ + (profiles==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(profiles)) ;
    __hash__result__ = 37*__hash__result__ + (expireDate==null ? 0 : expireDate.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (isDisabled ? 0 : 1) ;
    

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


head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.17;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandTransferBase.java;

1.1
date	2011.03.15.02.42.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandTransferBase.java;


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

// original type: ['http://soap-api.gftforex.com']:CommandTransferBase


public  class CommandTransferBase 
  extends com.gftforex.soap.api.CommandBase
  implements java.io.Serializable
{

  public CommandTransferBase() {}

  public CommandTransferBase(java.lang.String p_commandID
,     long p_accountID
,     double p_amount
,     java.lang.String p_currency) 
  {
    super(p_commandID);
     this.accountID = p_accountID;
     
     this.amount = p_amount;
     
     this.currency = p_currency;
     this.__isset_currency = true;


  }




  
  private long accountID ;

  /**
  <br>  Derived from AccountID.

  <br>  schema name = ['http://soap-api.gftforex.com']:AccountID
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:unsignedInt
  <br>  schema formQualified = true
  */
  public long getAccountID() {
    return accountID;
  }

  public void setAccountID(long v) {
    
    this.accountID = v;
    
  }





  
  private double amount ;

  /**
  <br>  Derived from Amount.

  <br>  schema name = ['http://soap-api.gftforex.com']:Amount
  <br>  schema type = ['http://soap-api.gftforex.com']:RoundSafeAmount
  <br>  schema formQualified = true
  */
  public double getAmount() {
    return amount;
  }

  public void setAmount(double v) {
    
    this.amount = v;
    
  }





  
  private java.lang.String currency ;

  /**
  <br>  Derived from Currency.

  <br>  schema name = ['http://soap-api.gftforex.com']:Currency
  <br>  schema type = ['http://soap-api.gftforex.com']:Currency
  <br>  schema formQualified = true
  */
  public java.lang.String getCurrency() {
    return currency;
  }

  public void setCurrency(java.lang.String v) {
    
    this.currency = v;
    this.__isset_currency = true;

  }

  private boolean __isset_currency;
  public boolean _isSetCurrency() {
    return this.__isset_currency;
  }
  public void _unsetCurrency() {
    this.__isset_currency = false;
  }









  public java.lang.String toString() {
  return "CommandTransferBase" + "{"
             + " accountID=<" + getAccountID() + ">"
             + " amount=<" + getAmount() + ">"
             + " currency=<" + getCurrency() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandTransferBase) {
      if (!super.equals(__other__)) return false;

      CommandTransferBase __obj__ =  (CommandTransferBase) __other__;


      return true
            && (accountID == __obj__.getAccountID())
            && (amount == __obj__.getAmount())
            && (currency==null ? __obj__.getCurrency()==null : currency.equals(__obj__.getCurrency()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    __hash__result__ = 37*__hash__result__ + (int)accountID ;
    __hash__result__ = 37*__hash__result__ + (int)amount ;
    __hash__result__ = 37*__hash__result__ + (currency==null ? 0 : currency.hashCode()) ;
    

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


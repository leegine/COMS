head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.03.04.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	WebServiceSoap.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package fx.client;

/**
 * Generated interface, do not edit.
 *
 * This stub interface was generated by weblogic
 * webservice stub gen on Tue Jul 29 13:33:51 JST 2008 */

public interface WebServiceSoap extends java.rmi.Remote{

  /**
   * EntryOshirase 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryOshiraseResponse entryOshirase(jp.co.hitachi.www.TFX.WebService.EntryOshirase parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for EntryOshirase 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryOshiraseOut entryOshirase(jp.co.hitachi.www.TFX.WebService.EntryOshiraseIn in0)
       throws java.rmi.RemoteException ;
  /**
   * DeleteOshirase 
   */

  public jp.co.hitachi.www.TFX.WebService.DeleteOshiraseResponse deleteOshirase(jp.co.hitachi.www.TFX.WebService.DeleteOshirase parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for DeleteOshirase 
   */

  public jp.co.hitachi.www.TFX.WebService.DeleteOshiraseOut deleteOshirase(jp.co.hitachi.www.TFX.WebService.DeleteOshiraseIn in0)
       throws java.rmi.RemoteException ;
  /**
   * ChangeRiyousya 
   */

  public jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaResponse changeRiyousya(jp.co.hitachi.www.TFX.WebService.ChangeRiyousya parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for ChangeRiyousya 
   */

  public jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaOut changeRiyousya(jp.co.hitachi.www.TFX.WebService.ChangeRiyousyaIn in0)
       throws java.rmi.RemoteException ;
  /**
   * InquirySyoriKekka 
   */

  public jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaResponse inquirySyoriKekka(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekka parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for InquirySyoriKekka 
   */

  public jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaOut inquirySyoriKekka(jp.co.hitachi.www.TFX.WebService.InquirySyoriKekkaIn in0)
       throws java.rmi.RemoteException ;
  /**
   * EntryRiyousya 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryRiyousyaResponse entryRiyousya(jp.co.hitachi.www.TFX.WebService.EntryRiyousya parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for EntryRiyousya 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryRiyousyaOut entryRiyousya(jp.co.hitachi.www.TFX.WebService.EntryRiyousyaIn in0)
       throws java.rmi.RemoteException ;
  /**
   * EntryReceipt 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryReceiptResponse entryReceipt(jp.co.hitachi.www.TFX.WebService.EntryReceipt parameters) 
       throws java.rmi.RemoteException ;
  /**
   * Convenience method for EntryReceipt 
   */

  public jp.co.hitachi.www.TFX.WebService.EntryReceiptOut entryReceipt(jp.co.hitachi.www.TFX.WebService.EntryReceiptIn in0)
       throws java.rmi.RemoteException ;
}@

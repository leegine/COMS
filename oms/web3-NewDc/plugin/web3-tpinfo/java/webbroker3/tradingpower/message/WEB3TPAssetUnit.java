head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �a�莑�Y���׃��j�b�g�N���X(WEB3TPAssetUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 Revision History : 2007/08/06 �g�E�N�|(���u) ���f��No.119
 Revision History : 2008/01/22 �И���(���u) ���f��No.231
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�a�莑�Y���׃��j�b�g)<BR>
 * �a�莑�Y���׃��j�b�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetUnit extends Message 
{
   
   /**
    * ���Z�ϋ敪
    */
   public String clearUpDiv;
   
   /**
    * �a���
    */
   public String accountBalance;
   
   /**
    * �a���<����������܂�>
    */
   public String accountBalanceDay;
   
   /**
    * �����]���z
    */
   public String equityAsset;
   
   /**
    * �����~�j�����]���z
    */
   public String mstkAsset;
   
   /**
    * �ݐϓ����]���z
    */
   public String ruitoAsset;
   
   /**
    * �����M���]���z
    */
   public String mutualAsset;
   
   /**
    * ���]���z
    */
   public String bondAsset;
   
   /**
    * ���v�]���z
    */
   public String totalAsset;

   /**
    * �a����i�h���j
    */
   public String accountBalanceDollar;
   
   /**
    * �a����i���[���j
    */
   public String accountBalanceEuro;

   /**
    * �O�������]���z
    */
   public String feqAsset;

   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B54A5F0065
    */
   public WEB3TPAssetUnit() 
   {
   }
   
}
@

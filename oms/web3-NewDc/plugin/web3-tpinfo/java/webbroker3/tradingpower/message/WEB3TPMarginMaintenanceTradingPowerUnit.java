head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginMaintenanceTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ۏ؋��ێ��]�̓��j�b�g�N���X(WEB3TPMarginMaintenanceTradingPowerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ۏ؋��ێ��]�̓��j�b�g)<BR>
 * �ۏ؋��ێ��]�̓��j�b�g�N���X�B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPMarginMaintenanceTradingPowerUnit extends Message 
{
   
   /**
    * �ۏ؋��ێ��� 
    */
   public String marginMaintenanceRate;
   
   /**
    * �ۏ؋��ێ��]��
    */
   public String marginMaintenanceTradingPower;
   
   /**
    * (�R���X�g���N�^�j
    * @@roseuid 41B58389027A
    */
   public WEB3TPMarginMaintenanceTradingPowerUnit() 
   {
    
   }
}
@

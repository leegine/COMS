head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Y�]���z���𖾍� (WEB3TPAssetHistoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25  䈋�(���u) �V�K�쐬
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���Y�]���z���𖾍�)<BR>
 * ���Y�]���z���𖾍׃N���X�B<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3TPAssetHistoryUnit extends Message 
{
   
   /**
    * (���t)<BR>
    * ���<BR>
    */
   public Date bizDate;
   
   /**
    * (�a����]���z)<BR>
    * �a����i�����EMRF�j�]���z <BR>
    */
   public String accountBalance;
   
   /**
    * (�����]���z)<BR>
    * �����]���z<BR>
    */
   public String equityAsset;
   
   /**
    * (�����~�j�����]���z)<BR>
    * �����~�j�����]���z<BR>
    */
   public String mstkAsset;
   
   /**
    *(�ݐϓ����]���z)<BR>
    * �ݐϓ����]���z <BR>
    */
   public String ruitoAsset;
   
   /**
    * (�����M���]���z)<BR>
    * �����M���]���z<BR>
    */
   public String mutualAsset;
   
   /**
    * (���]���z)<BR>
    * ���]���z <BR>
    */
   public String bondAsset;
   
   /**
    * (���v�]���z)<BR>
    * �]���z�̍��v<BR>
    */
   public String totalAsset;
   
   /**
    * (�O����)<BR>
    * ���v�]���z�̑O���� <BR>
    * ���O���ȑO�̃f�[�^�̏ꍇ�́A�O���� <BR>
    */
   public String comparedPreviousDay;
      
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B54A5F0065
    */
   public WEB3TPAssetHistoryUnit() 
   {
   }
   
}
@

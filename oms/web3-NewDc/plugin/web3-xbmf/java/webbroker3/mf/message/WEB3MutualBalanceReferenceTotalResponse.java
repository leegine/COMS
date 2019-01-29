head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�c�����v���X�|���X�N���X(WEB3MutualBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��536
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�c���Ɖ�c�����v���X�|���X)<BR>
 * ���M�c���Ɖ�c�����v���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceTotalResponse extends WEB3GenResponse 
{    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_balance_reference_total";
   
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412281618L;
   
   /**
    * (��������]���z���v)<BR>
    * ��������]���z���v
    */
   public String capitalGainTotalAsset = "0";
   
   /**
    * (��������]�����v���v)<BR>
    * ��������]�����v���v
    */
   public String capitalGainTotalAssetProfitLoss = "0";
   
   /**
    * ( ��ʌ����]���z���v)<BR>
    * ��ʌ����]���z���v
    */
   public String normalAccountTotalAsset = "0";
   
   /**
    * (��ʌ����]�����v���v)<BR>
    * ��ʌ����]�����v���v
    */
   public String normalAccountTotalAssetProfitLoss = "0";

    /**
     * (�O��MMF�]���z���v)<BR>
     * �O��MMF�]���z���v
     */
    public String frgnMmfTotalAsset = "0";

   /**
    * �R���X�g���N�^�B<BR>
    * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
    * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
    */
   public WEB3MutualBalanceReferenceTotalResponse(WEB3GenRequest l_request)
   {
       super(l_request);
   } 
   
   /**
    * @@roseuid 41D13CC1005D
    */
   public WEB3MutualBalanceReferenceTotalResponse() 
   {
    
   }
}
@

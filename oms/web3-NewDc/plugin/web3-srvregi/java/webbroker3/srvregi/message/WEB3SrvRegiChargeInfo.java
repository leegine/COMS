head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiChargeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���ԗ������(WEB3SrvRegiChargeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�T�[�r�X���p���ԗ������)<BR>
 * �T�[�r�X���p���ԗ������f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiChargeInfo extends Message 
{
    
    /**
     * (���p����ID)<BR>
     * �ʔ�<BR>
     */
    public String chargeId;
    
    /**
     * (���p���ԒP�ʋ敪)<BR>
     * 1:�N�@@2:���@@3:��<BR>
     */
    public String chargeDiv;
    
    /**
     * (���p����)
     */
    public String chargePeriod;
    
    /**
     * (���p����)
     */
    public String chargeAmt;
    
    /**
     * (�����敪)<BR>
     * true:�����@@false:�L��<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (�T�[�r�X���p���ԗ������)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40EE322B00C2
     */
    public WEB3SrvRegiChargeInfo() 
    {
     
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.02.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenOfficeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ζ�����(WEB3AccOpenOfficeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�Ζ�����)<BR>
 * �Ζ�����<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenOfficeInfo extends Message 
{
    
    /**
     * (�E�Ƌ敪)<BR>
     * �E�Ƌ敪<BR>
     * <BR>
     * �i�� ���ڒl�ɂ��Ă͊e�ЃJ�X�^�}�C�Y����j<BR>
     */
    public String occupationDiv;
    
    /**
     * (�Ζ��於��)<BR>
     * �Ζ��於��<BR>
     */
    public String officeName;
    
    /**
     * (�Ζ���X�֔ԍ�)<BR>
     * �Ζ���X�֔ԍ�<BR>
     */
    public String officeZipCode;
    
    /**
     * (�Ζ���Z��)<BR>
     * �Ζ���Z��<BR>
     */
    public String officeAdress;
    
    /**
     * (�Ζ���d�b�ԍ�)<BR>
     * �Ζ���d�b�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String officeTelephone;
    
    /**
     * (�Ζ���e�`�w�ԍ�)<BR>
     * �Ζ���e�`�w�ԍ�<BR>
     * <BR>
     * ���@@"-"���܂ޕ�����<BR>
     */
    public String officeFaxTelephone;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String department;
    
    /**
     * (��E��)<BR>
     * ��E��<BR>
     */
    public String position;
    
    /**
     * @@roseuid 41B45E790157
     */
    public WEB3AccOpenOfficeInfo() 
    {
     
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountMailAddressInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq���[���A�h���X���(WEB3AccInfoAccountMailAddressInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�ڋq���[���A�h���X���)<BR>
 * �ڋq���[���A�h���X���<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoAccountMailAddressInfo extends Message 
{
    
    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X<BR>
     */
    public String mailAddress;
    
    /**
     * (�X�V��)<BR>
     * �X�V��<BR>
     */
    public Date updateDate;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (���M�t���O)<BR>
     * ���M�t���O<BR>
     */
    public String sendFlag;
    
    /**
     * @@roseuid 418F386201B5
     */
    public WEB3AccInfoAccountMailAddressInfo() 
    {
     
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ē����[���z�M�w�����(WEB3AccInfoMailDistributionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�ē����[���z�M�w�����)<BR>
 * �ē����[���z�M�w�����<BR>
 */
public class WEB3AccInfoMailDistributionInfo extends Message 
{    
    /**
     * (ID)<BR>
	 *	�ē����[���z�M�w��ID<BR>
     */
    public String id;
    
    /**
     * (�z�M�w������)<BR>
     */
    public Date distributionRequestDate;
    
    /**
     * (�z�M�ڋq��)<BR>
     */
    public String distributionAccountNumber;
    
    /**
     * (�S�ڋq�t���O)<BR>
     * true�F�@@�S�ڋq�ɔz�M<BR>
     * false�F�@@�ē����[����]�q�݂̂ɔz�M<BR>
     */
    public boolean allAccountFlag;
    
    /**
     * (����)<BR>
     *<BR>
     *  �� encode�����l<BR>
     */
    public String mailSubject;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     *<BR>
     *  ���@@�z�M�w����<BR>
     */
    public String updaterCode;
    
    /**
     * (�ē����[���z�M�w�����)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailDistributionInfo
     * @@roseuid 415BCF700005
     */
    public WEB3AccInfoMailDistributionInfo() 
    {
     
    }
}
@

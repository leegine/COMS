head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticBranchRecruitLimitInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������X�ʉ���g���(WEB3BondDomesticBranchRecruitLimitInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.215
*/
package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���������X�ʉ���g���)<BR>
 * ���������X�ʉ���g���<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticBranchRecruitLimitInfo extends Message
{

    /**
     * (�������z���v)<BR>
     * �������z���v<BR>
     */
    public String orderAmountTotal;

    /**
     * (WEB3����g)<BR>
     * WEB3����g<BR>
     */
    public String web3RecruitLimit;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (���������X�ʉ���g���)<BR>
     * �R���X�g���N�^
     * @@roseuid 468D91690360
     */
    public WEB3BondDomesticBranchRecruitLimitInfo()
    {

    }
}
@

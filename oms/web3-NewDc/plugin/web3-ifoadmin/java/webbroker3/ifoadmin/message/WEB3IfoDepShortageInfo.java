head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoDepShortageInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����s���󋵏��(WEB3IfoDepShortageInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�؋����s���󋵏��)<BR>
 * �؋����s���󋵏��N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3IfoDepShortageInfo extends Message
{

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
     * (�����z)<BR>
     */
    public String claimAmount;

    /**
     * (���ݖ������z)<BR>
     */
    public String curNonPayAmt = null;

    /**
     * (���ݏ؋������v�z)<BR>
     */
    public String curIfoDepositNecessaryAmt;

    /**
     * (���ʗL���t���O)<BR>
     * ���ʗL���t���O<BR>
     * �iOP���������j<BR>
     * <BR>
     * true�F�@@�L <BR>
     * false�F�@@��<BR>
     * <BR>
     */
    public boolean contractExistFlag;

    /**
     * (�����L���t���O)<BR>
     * �����L���t���O<BR>
     * �iOP���������j<BR>
     * <BR>
     * true�F�@@�L<BR>
     * false�F�@@��<BR>
     * <BR>
     * <BR>
     */
    public boolean orderExistFlag;

    /**
     * (�؋����s���󋵏��)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4998F2BB0071
     */
    public WEB3IfoDepShortageInfo()
    {

    }
}
@

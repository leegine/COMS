head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U����ꗗ�T�[�r�X�����N���X(WEB3AdminInformProfDistSellTransSrcListServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/05 �Ӑ�(���u) �V�K�쐬 ���f��No.054
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�e����Z�@@�֏��)<BR>
 * �e����Z�@@�֏��
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcInfo extends Message
{

    /**
     * (���Z�@@�֖�)<BR>
     * ���Z�@@�֖�
     */
    public String financialInstitutionName;

    /**
     * (���Z�@@�֖��i�J�i�j)<BR>
     * ���Z�@@�֖��i�J�i�j
     */
    public String financialInstitutionNameKana;

    /**
     * (�x�X��)<BR>
     * �x�X��
     */
    public String financialBranchName;

    /**
     * (�x�X���i�J�i�j)<BR>
     * �x�X���i�J�i�j
     */
    public String financialBranchNameKana;

    /**
     * (�e����Z�@@�֏��)<BR>
     * �R���X�g���N�^
     * @@roseuid 46496A8F003D
     */
    public WEB3AdminInformProfDistSellTransSrcInfo()
    {

    }
}
@

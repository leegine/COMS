head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialBranchSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݎx�X�������X�|���X(WEB3AccOpenFinancialBranchSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����J�ݎx�X�������X�|���X)<BR>
 * �����J�ݎx�X�������X�|���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenFinancialBranchSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_financialBranchSearch";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081616L;

    /**
     * (�x�X��)<BR>
     * �x�X���i�����j�̔z��<BR>
     */
    public String[] financialBranchName;

    /**
     * (�x�X���i�J�i�j)<BR>
     * �x�X���i�J�i�j�̔z��<BR>
     */
    public String[] financialBranchNameKana;

    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h�̔z��<BR>
     */
    public String[] financialBranchCode;

    /**
     * @@roseuid 41B45E7D003E
     */
    public WEB3AccOpenFinancialBranchSearchResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccOpenFinancialBranchSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݋��Z�@@�֌������X�|���X(WEB3AccOpenFinancialSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����J�݋��Z�@@�֌������X�|���X)<BR>
 * �����J�݋��Z�@@�֌������X�|���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenFinancialSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_financialSearch";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081615L;

    /**
     * (��s��)<BR>
     * ��s���i�����j�̔z��<BR>
     */
    public String[] financialInstitutionName;

    /**
     * (��s���i�J�i�j)<BR>
     * ��s���i�J�i�j�̔z��<BR>
     */
    public String[] financialInstitutionNameKana;

    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h�̔z��<BR>
     */
    public String[] financialInstitutionCode;

    /**
     * @@roseuid 41B45E7C03A9
     */
    public WEB3AccOpenFinancialSearchResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AccOpenFinancialSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@

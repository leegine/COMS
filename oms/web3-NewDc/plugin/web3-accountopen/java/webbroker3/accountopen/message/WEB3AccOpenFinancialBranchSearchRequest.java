head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialBranchSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�ݎx�X�������N�G�X�g(WEB3AccOpenFinancialBranchSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ���w�� �V�K�쐬
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����J�ݎx�X�������N�G�X�g)<BR>
 * �����J�ݎx�X�������N�G�X�g<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AccOpenFinancialBranchSearchRequest extends WEB3GenRequest
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
     * (��s��)<BR>
     * ��s��<BR>
     */
    public String financialInstitutionName;

    /**
     * (�擪����)<BR>
     * �擪����<BR>
     * <BR>
     * �� ���p�J�i�ɂĎw��<BR>
     * �� �w�肪�Ȃ��ꍇ�͂��ׂ�<BR>
     */
    public String headCharacter;

    /**
     * @@roseuid 41B45E7D00AB
     */
    public WEB3AccOpenFinancialBranchSearchRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenFinancialBranchSearchResponse(this);
    }
}
@

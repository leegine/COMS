head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������������̓��N�G�X�g(WEB3EquityChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
Revesion History : 2004/12/08 �����a��(SRA) �c�Č��Ή� �m��.�O�T�V
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������������̓��N�G�X�g�j�B<BR>
 * <BR>
 * �������������������͗v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityChangeInputRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeInputRequest.class);

    /**
     * (ID)<BR>
     * ����ID<BR>
     */
    public String id;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004052001;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 40611EB7000C
     */
    public WEB3EquityChangeInputRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.equity.message.WEB3EquityChangeInputResponse
     * @@roseuid 40611F9C0377
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityChangeInputResponse(this);
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A<BR>
     * �@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag   : BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������ڍ׃��N�G�X�g(WEB3EquityExecuteDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �����F (���u) �V�K�쐬
Revesion History : 2004/12/07 �����a��(SRA) �c�Č��Ή� �m��.�O�T�V
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i���������������ڍ׃��N�G�X�g�j�B<BR>
 * <BR>
 * ���������������ڍחv���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityExecuteDetailsRequest extends WEB3GenRequest
{

    /**
     * (ID)<BR>
     * ����ID<BR>
     * �i�Ɖ��ʂ���ڍ׉�ʂւ̑J�ڎ��̂ݎg���j<BR>
     */
    public String id;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_details";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405111057L;

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteDetailsRequest.class);

    /**
     * (���������������ڍ׃��N�G�X�g)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 406A8AAB02CD
     */
    public WEB3EquityExecuteDetailsRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@param requestData - (���N�G�X�g�f�[�^)<BR>
     * ���������������ڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 406A8A75005C
     */
    public WEB3GenResponse createResponse(WEB3EquityExecuteDetailsRequest l_request)
    {
        return new WEB3EquityExecuteDetailsResponse(l_request);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40A5B2B60232
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityExecuteDetailsResponse(this);
    }
    
    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@ID�`�F�b�N<BR>
     * �@@this.ID��null�̏ꍇ�A<BR>
     * �@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00600<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            this.getClass().getName() + "validate");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@

head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoExecMailDistributionChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g(WEB3AccInfoExecMailDistributionChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoExecDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoProductTypeDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g)<BR>
 * ���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoExecMailDistributionChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_execMailDistributionChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082156L;

    /**
     * (�����^�C�v�敪)<BR>
     * �����^�C�v�敪<BR>
     * <BR>
     * 1�F�@@�����iEquity�j<BR>
     * 6�F�@@�����w���敨�^�I�v�V�����iIfo�j<BR>
     */
    public String productTypeDiv;

    /**
     * (���^�����敪)<BR>
     * ���^�����敪<BR>
     * <BR>
     * 0�F�@@����胁�[���ݒ�<BR>
     * 1�F�@@��胁�[���ݒ�<BR>
     */
    public String execDiv;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * @@roseuid 418F39F20186
     */
    public WEB3AccInfoExecMailDistributionChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoExecMailDistributionChangeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�����^�C�v�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01109<BR>
     * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01110<BR>
     * <BR>
     * �Q�j�@@���^�����敪�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01111<BR>
     * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01112<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C0916002C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.productTypeDiv == null || "".equals(this.productTypeDiv))
        {
            log.error("�����^�C�v�敪�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01109, getClass().getName() + STR_METHOD_NAME, "�����^�C�v�敪������");
        }
        if ( !WEB3AccInfoProductTypeDivDef.EQUITY.equals(this.productTypeDiv)
             && !WEB3AccInfoProductTypeDivDef.IFO.equals(this.productTypeDiv) )
        {
            log.error("�����^�C�v�敪�s���ȃR�[�h�l�̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01110, getClass().getName() + STR_METHOD_NAME, "�����^�C�v�敪�s���ȃR�[�h�l");     
        }
        
        if (this.execDiv == null || "".equals(this.execDiv))
        {
            log.error("���^�����敪�����͂̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01111, getClass().getName() + STR_METHOD_NAME, "���^�����敪������");
        }
        if ((!WEB3AccInfoExecDivDef.EXEC_MAIL.equals(this.execDiv))
            && (!WEB3AccInfoExecDivDef.UNEXEC_MAIL.equals(this.execDiv)))
        {
            log.error("���^�����敪�s���ȃR�[�h�l�̏ꍇ�A��O���X���[");
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01112, getClass().getName() + STR_METHOD_NAME, "���^�����敪�s���ȃR�[�h�l");
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@

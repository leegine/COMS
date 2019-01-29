head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬���N�G�X�g(WEB3AdminEquityForcedSettleTempOrderCreateRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��.132
Revision History : 2007/04/27 ��іQ (���u) ���f��.138,147
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.define.WEB3ForcedSettleTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������ω������쐬���N�G�X�g)<BR>
 * �������ω������쐬���N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleTempOrderCreateRequest extends WEB3BackRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_temp_order_create";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �������ω������쐬�Ώۂ̏،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (From����ID)<BR>
     * From����ID<BR>
     */
    public long rangeFrom;

    /**
     * (To����ID)<BR>
     * To����ID<BR>
     */
    public long rangeTo;

    /**
     * (�������Ϗ����敪)<BR>
     * �������Ϗ����敪<BR>
     * <BR>
     * 1�F�@@�I�����C���J�n�O<BR>
     * 2�F�@@�x�e���ԑ�<BR>
     */
    public String forcedSettleType;

    /**
     * @@roseuid 462CA4260331
     */
    public WEB3AdminEquityForcedSettleTempOrderCreateRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@this.�،���ЃR�[�h��null�̏ꍇ�A<BR>
     * �@@�u�،���ЃR�[�h��null�v�̗�O��throw����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00827<BR>
     * <BR>
     * �Q�j�@@�������Ϗ����敪�`�F�b�N <BR>
     * �@@�Q�|�P�j�@@this.�������Ϗ����敪��null�ł������ꍇ <BR>
     * �@@�@@�@@�@@�u�������Ϗ����敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02762<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.�������Ϗ����敪�ɉ��L�̒l�ȊO���܂܂�Ă���ꍇ�A<BR>
     * �@@�@@�u�������Ϗ����敪������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@�E"�I�����C���J�n�O"<BR>
     * �@@�@@�E"�x�e���ԑ�"<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02774<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4603762402E6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        // �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
        // �P�j�@@�،���ЃR�[�h�`�F�b�N
        // �@@this.�،���ЃR�[�h��null�̏ꍇ�A
        // �@@�u�،���ЃR�[�h��null�v�̗�O��throw����B
        if (this.institutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }

        // �Q�j�@@�������Ϗ����敪�`�F�b�N
        // �@@�Q�|�P�j�@@this.�������Ϗ����敪��null�ł������ꍇ
        // �@@�@@�@@�@@�u�������Ϗ����敪��null�v�̗�O���X���[����B
        if (this.forcedSettleType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02762,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������Ϗ����敪��null�B");
        }

        // �@@�Q�|�Q�j�@@this.�������Ϗ����敪�ɉ��L�̒l�ȊO���܂܂�Ă���ꍇ�A
        // �@@�@@�u�������Ϗ����敪������`�̒l�v�̗�O���X���[����B
        // �@@�@@�E"�I�����C���J�n�O"
        // �@@�@@�E"�x�e���ԑ�"
        if ((!WEB3ForcedSettleTypeDef.BEFORE_ONLINE.equals(this.forcedSettleType))
            && (!WEB3ForcedSettleTypeDef.REST_TIMEZONE.equals(this.forcedSettleType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02774,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������Ϗ����敪������`�̒l�B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AdminEquityForcedSettleTempOrderCreateResponse(this);
    }
}
@

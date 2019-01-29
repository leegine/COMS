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
filename	WEB3AdminEquityForcedSettleOrderApproveMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g(WEB3AdminEquityForcedSettleOrderApproveMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ��іQ (���u) �V�K�쐬 ���f��No.128
Revision History : 2007/05/16 ��іQ (���u) ���f��No.152
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveMainRequest extends WEB3BackRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainRequest.class);

    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_forced_settle_order_approve_main";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (�X���b�hNo)<BR>
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * (From����ID)<BR>
     * From����ID<BR>
     */
    public Long rangeFrom;

    /**
     * (To����ID)<BR>
     * To����ID<BR>
     */
    public Long rangeTo;

    /**
     * (���F�敪)<BR>
     * ���F�敪<BR>
     * <BR>
     * 0�F�@@���F<BR>
     * 1�F�@@�񏳔F<BR>
     */
    public String approveType;

    /**
     * (����ID�ꗗ)<BR>
     * ����ID�ꗗ<BR>
     */
    public String[] orderIdList;

    /**
     * (�Ǘ���ID)<BR>
     * �Ǘ���ID<BR>
     */
    public Long administratorId;

    /**
     * (�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest
     * @@roseuid 460755CC03C2
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00827<BR>
     * <BR>
     * �Q�j�@@�X���b�hNo�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A<BR>
     * �@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01974<BR>
     * <BR>
     * �R�j�@@From����ID�`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A<BR>
     * �@@�@@�uFrom����ID�������́v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02421<BR>
     * <BR>
     * �S�j�@@To����ID�`�F�b�N<BR>
     * �@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A<BR>
     * �@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02422<BR>
     * <BR>
     * �T�j�@@���F�敪�`�F�b�N<BR>
     * �@@�T�|�P�j�@@this.���F�敪 == null�̏ꍇ�A<BR>
     * �@@�@@�u���F�敪��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02761<BR>
     * <BR>
     * �U�j�@@����ID�ꗗ�`�F�b�N<BR>
     * �@@�U�|�P�j�@@this.����ID�ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�uID��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00600<BR>
     * <BR>
     * �V�j�@@�Ǘ���ID�`�F�b�N
     * �@@�U�|�P�j�@@this�Ǘ���ID == null�̏ꍇ�A
     * �@@�@@�uID��null�v�̗�O���X���[����B 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02776<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46075A5D03A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

       // �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
       // �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
       // �P�j�@@�،���ЃR�[�h�`�F�b�N
       // �@@�P�|�P�j�@@this.�،���ЃR�[�h == null�̏ꍇ�A
       // �@@�@@�u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B
       if (this.institutionCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }

       // �Q�j�@@�X���b�hNo�`�F�b�N
       // �@@�Q�|�P�j�@@this.�X���b�hNo == null�̏ꍇ�A
       // �@@�@@�u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B
       if (this.threadNo == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01974,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "�X���b�h�ԍ��̎w��Ȃ��B");
       }

       // �R�j�@@From����ID�`�F�b�N
       // �@@�R�|�P�j�@@this.From����ID == null�̏ꍇ�A
       // �@@�@@�uFrom����ID�������́v�̗�O���X���[����B
       if (this.rangeFrom == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02421,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "From����ID�������͂ł��B");
       }

       // �S�j�@@To����ID�`�F�b�N
       // �@@�S�|�P�j�@@this.To����ID == null�̏ꍇ�A
       // �@@�@@�uTo����ID�i���j�������́v�̗�O���X���[����B
       if (this.rangeTo == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02422,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "To����ID�i���j�������͂ł��B");
       }

       // �T�j�@@���F�敪�`�F�b�N
       // �@@�T�|�P�j�@@this.���F�敪 == null�̏ꍇ�A
       // �@@�@@�u���F�敪��null�v�̗�O���X���[����B
       if (this.approveType == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02761,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "���F�敪��null�B");
       }

       // �U�j�@@����ID�ꗗ�`�F�b�N
       // �@@�U�|�P�j�@@this.����ID�ꗗ == null�̏ꍇ�A
       // �@@�@@�uID��null�v�̗�O���X���[����B
       if (this.orderIdList == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00600,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "����ID�����w��ł��B");
       }

       //�V�j�@@�Ǘ���ID�`�F�b�N 
       //�U�|�P�j�@@this�Ǘ���ID == null�̏ꍇ�A 
       //�uID��null�v�̗�O���X���[����B
       if (this.administratorId == null)
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02776,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "ID��null�B");
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
        return new WEB3AdminEquityForcedSettleOrderApproveMainResponse(this);
    }
}
@

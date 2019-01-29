head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���ʏƉ�N�G�X�g(WEB3FuturesContractReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨���ʏƉ�N�G�X�g)<BR>
 * �����w���敨���ʏƉ��ʕ\�����N�G�X�g�N���X<BR>
 *                                                                     
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesContractReferenceRequest extends WEB3GenRequest
{
    /**         
     * ���O�o�̓��[�e�B���e�B�B         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_contractReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407201055L;

    /**
     * (�����R�[�h)<BR>
     * �����w���敨�����R�[�h<BR>
     * <BR>
     * �X�����O�V���b�g�N���⌟���������A�����w��̏ꍇ<BR>
     */
    public String futProductCode;

    /**
     * (���Ϗ�ԋ敪)<BR>
     * (���������w�莞�Ɏg�p)<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 0�F���ύ�<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     */
    public String settlementState;

    /**
     * (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
     * �Ώۍ��ځF�����R�[�h�A���N�����A���v�A���敪<BR>
     * ����͉�Ђ��ƂɌ��߂�ꂽ�����l���Z�b�g
     * ���o�C���ł́u�����R�[�h�̏����v�Œ�
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �\�����������y�[�W�ʒu���w��@@<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 40C09E620177
     */
    public WEB3FuturesContractReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���Ϗ�ԋ敪�̃`�F�b�N<BR>
     * �@@�@@�@@�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�Enull(�w��Ȃ�) <BR>
     * �@@�@@�@@�E0(���ύ�)<BR>
     *       �E1(������)<BR>
     *       �E2(���ϒ�)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00233<BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�@@�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E���v<BR>
     * �@@�@@�@@�@@�E���敪<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     * ��O�Ƃ���B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00091<BR>
     * @@throws WEB3BaseException
     * @@roseuid 406BE86200BA
     */
    public void validate() throws WEB3BaseException
    {   
        log.entering("validate()");
        //�P�j�@@���Ϗ�ԋ敪�̃`�F�b�N
        log.debug("�P�j�@@���Ϗ�ԋ敪�̃`�F�b�N: ENTER");
        
        //�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B
        //null(�w��Ȃ�)
        //0(���ύ�)
        //1(������)
        //2(���ϒ�)
        if ((this.settlementState != WEB3IfoSettlementStateDef.NOT_DESIGNATION) 
            && !WEB3IfoSettlementStateDef.SETTLEMENT_END.equals(this.settlementState)
            && !WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
            && !WEB3IfoSettlementStateDef.SETTLING.equals(this.settlementState))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00233,
                getClass().getName() + ".validate()",
                "���Ϗ�ԋ敪��null(�w��Ȃ�) �A0(���ύ�)�A1(������)�A2(���ϒ�)�ȊO�ł���ꍇ���G���[");
        }
        log.debug("�P�j�@@���Ϗ�ԋ敪�̃`�F�b�N: END");

        //�Q�j�@@�\�[�g�L�[�̃`�F�b�N
        //�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B
        //�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B
        //�E����
        //�E����
        //�E���v
        //�E���敪
        if(this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                getClass().getName() + ".validate()",
                "�\�[�g�L�[�������A�����A���v�A���敪�ȊO�̏ꍇ�G���[");
        }
        
        int l_intOpSortKeysLength = this.futOpSortKeys.length;
        log.debug("l_intOpSortKeysLength: " + l_intOpSortKeysLength);
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            log.debug("LOOP: ENTER " + (i+1));
            if (           
                !WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.OPEN_DATE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + ".validate()",
                    "�\�[�g�L�[�������A�����A���v�A���敪�ȊO�̏ꍇ�G���[");
            }
            log.debug("LOOP: END " + (i+1));
        }

        //�R�j�@@�v���y�[�W�ԍ��̃`�F�b�N
        //���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A
        //���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B: ENTER");
            pageIndex = "1";
            log.debug("���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B: END");
        }
        log.debug("�R�j�@@�v���y�[�W�ԍ��̃`�F�b�N: END");

        //�S�j�@@�y�[�W���\���s���̃`�F�b�N
        log.debug("�S�j�@@�y�[�W���\���s���̃`�F�b�N: ENTER");
        //���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A
        //��O�Ƃ���
        if (this.pageSize.equals("0") 
            || this.pageSize.equals("") 
            || WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + ".validate()",
                "�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�B");
        }
        log.debug("�S�j�@@�y�[�W���\���s���̃`�F�b�N: END");
        log.exiting("validate()");
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesContractReferenceResponse(this);
    }
}
@
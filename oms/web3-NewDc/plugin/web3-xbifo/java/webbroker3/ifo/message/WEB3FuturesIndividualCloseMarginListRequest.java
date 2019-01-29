head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ʕԍψꗗ��ʕ\�����N�G�X�g(WEB3FuturesIndividualCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 羉s (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���敨�ʕԍψꗗ��ʕ\�����N�G�X�g)<BR>
 * <BR>
 * �����w���敨�ʕԍψꗗ��ʕ\�����N�G�X�g�N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesIndividualCloseMarginListRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191445L;

    /**
     * (�����w���敨�I�v�V�����\�[�g�L�[)<BR>
     * <BR>
     * �����̍~�����w��<BR>
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (ID)<BR>
     * <BR>
     * �z��̊e�l�ɂ͌���ID��ݒ�<BR>
     */
    public String[] id;

    /**
     * @@roseuid 40F7AE1002FD
     */
    public WEB3FuturesIndividualCloseMarginListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��<BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * �@@�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * �@@�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� �� <BR>
     *                  null �̏ꍇ�A��O���X���[����B<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00085<BR>
     * �@@�@@�P�|�R�|�Q�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� �� <BR>
     *                "����" �̏ꍇ�A��O���X���[����B<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00317<BR>
     * �@@�@@�P�|�R�|�R�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� �� <BR>
     *                 null �̏ꍇ�A��O���X���[����B<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00318<BR>
     * �@@�@@�P�|�R�|�S�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� �� <BR>
     *                 "D" �̏ꍇ�A��O���X���[����B<BR>
     *                  �ED�F�~��<BR>
     *                    A�F����
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00080<BR>
     * �@@�Q�|�Q�jthis.�h�c�̗v�f�����O�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00282<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2F4540214
     */
    public void validate() throws WEB3BaseException
    {
        //�P�j�@@�\�[�g�L�[�`�F�b�N 
        //�@@�P�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��
        //�@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B
        if (this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                getClass().getName() + "validate",
                "�\�[�g�L�[��null�̒l�ł���B");
        }
        
        //�@@�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����
        //�@@�@@�@@�@@�O�ł���Η�O���X���[����B
        if (this.futOpSortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "validate",
                "�\�[�g�L�[�̗v�f�����O�ł���B");
        }
        
        //�@@�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����
        //�@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B 
        int l_intOpSortKeysLength = this.futOpSortKeys.length;
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            //�@@�@@�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B 
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                    getClass().getName() + "validate",
                    "�L�[���ڂ�null�̒l�ł���B");
            }
            
            //�@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��
            //�@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B
            //�@@�@@�@@�E����
            if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "validate",
                    "�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� ��  �u�����v�ł���");
            }
            
            //�@@�@@�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    getClass().getName() + "validate",
                    "�����^�~�������w��ł��B");
            }
            
            //�@@�@@�P�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
            //�@@�@@�@@�E�hD�F�~���h
            //          A�F����
            if (!WEB3AscDescDef.DESC.equals(this.futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.ASC.equals(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "validate",
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        //�Q�j�@@�h�c�`�F�b�N
        //�@@�Q�|�P�jthis.�h�c��null�̒l�ł���Η�O���X���[����B
        if (this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }
        
        //�@@�Q�|�Q�jthis.�h�c�̗v�f�����O�ł���Η�O���X���[����B
        else if (this.id.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00282, 
                getClass().getName() + "validate",
                "ID�̗v�f����0�ł���B");
        }
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE10031C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesIndividualCloseMarginListResponse(this);

    }
}
@

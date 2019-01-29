head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�ꗗ���N�G�X�g�N���X(WEB3MstkSellListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�����~�j�������t�ꗗ���N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�������t�ꗗ���N�G�X�g�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellListRequest extends WEB3GenRequest 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellListRequest.class);
    
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_sellList";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
    /**
     * �i�\�[�g�L�[�j�B<BR>
     * <BR>
     * �����~�j�����\�[�g�L�[�̈ꗗ<BR>
     * <BR>
     * �Ώۍ��ځF �����R�[�h�A�s��R�[�h
     */
    public WEB3MstkSortKey[] sortKeys;
    
    /**
     * �i�v���y�[�W�ԍ��j�B<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * �i�y�[�W���\���s���j�B<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s��
     */
    public String pageSize;
    
    /**
     * �i�����~�j�������t�ꗗ���N�G�X�g�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
    public WEB3MstkSellListRequest() 
    {
    }
    
    /**
     * �ivalidate�j�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�@@�P�|�P�jthis.�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�@@�P�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00813<BR>
     * <BR>
     * �@@�@@�P�|�R�jthis.�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B <BR>
     * �@@�@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * �@@�@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�E�����R�[�h<BR>
     * �@@�@@�@@�E�s��R�[�h<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�@@�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�@@�@@�@@this�D�v���y�[�W�ԍ����A���w��̏ꍇ�A<BR>
     * �@@�@@�@@�@@this�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@this.�v���y�[�W�ԍ����A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ���null(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ�������(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�v���y�[�W�ԍ����O(�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�@@�@@�@@this.�y�[�W���\���s�����A�ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s����null(�u�y�[�W���\���s����null�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s��������(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�@@�@@�Ethis.�y�[�W���\���s�����O(�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>�@@
     * @@roseuid 41121DB901E4
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�@@�\�[�g�L�[�`�F�b�N
        log.debug("�\�[�g�L�[�`�F�b�N");
        if(sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        for(int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
            log.debug("sortKeys[i].keyItem = " + sortKeys[i].keyItem);
            if(!(WEB3EquityKeyItemDef.PRODUCTCODE).equals(sortKeys[i].keyItem) && 
                !(WEB3EquityKeyItemDef.TRADEMARKET).equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
        }

        //�Q�j�@@�@@�v���y�[�W�ԍ��`�F�b�N
        log.debug("�v���y�[�W�ԍ��`�F�b�N");
        if(pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                this.getClass().getName() + STR_METHOD_NAME);    
        }
        if(!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblIndex = Double.parseDouble(pageIndex);
        if(l_dblIndex <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�R�j�@@�@@�y�[�W���\���s���`�F�b�N
        log.debug("�y�[�W���\���s���`�F�b�N");
        if(pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        if(!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        double l_dblSize = Double.parseDouble(pageSize);
        if(l_dblSize <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
    }
    
    /**
     * �icreate���X�|���X�j�B<BR>
     * <BR>
     * @@return WEB3GenResponse �����~�j�������t�ꗗ���X�|���X
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkSellListResponse(this);
    }
}
@

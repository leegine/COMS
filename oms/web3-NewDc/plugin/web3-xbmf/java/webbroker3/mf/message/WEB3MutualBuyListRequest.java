head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�ꗗ�Ɖ�N�G�X�g�N���X(WEB3MutualBuyListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 ���E (���u) �V�K�쐬
Revesion History : 2004/08/23 ������ (���u) ���r���[ 
Revesion History : 2005/10/21 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/09/19 ���� (���u) �d�l�ύX�E���f��482
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��535
*/
package webbroker3.mf.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFReferenceDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * �����M�����t�ꗗ�Ɖ�N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyListRequest extends WEB3GenRequest
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_list";

    /**
     * (�Ɖ�敪)<BR>
     * �Ɖ�敪 <BR>
     * <BR>
     * 0�F ���t�ꗗ <BR>
     * 1�F �ژ_�����X�������ꗗ<BR> 
     * 2�F ���t�̂� <BR>
     * 3�F ��W�̂�<BR> 
     * <BR>
     * ��0�F ���t�ꗗ�̏ꍇ�A���t/��W�Ƃ��Ɖ�̑ΏۂƂ���B <BR>
     */
    public String referenceType;
    
    /**
     * ���M�����J�e�S���[�R�[�h<BR>
     * <BR>
     * (null�̏ꍇ�͎w�薳���Ƃ���)<BR>
     */
    public String categoryCode;
    
    /**
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     * <BR>
     * (���y�[�W���O���s��Ȃ��ꍇ�A"1"�Œ�Ƃ���B)<BR>
     */
    public String pageIndex;
    
    /**
     * �y�[�W���\���s��<BR>
     * 1�y�[�W���ɕ\�����������s�����w��<BR>
     * <BR>
     * (���y�[�W���O���s��Ȃ��ꍇ�A�ő�l�ƂȂ鐔�l���w�肷��)
     */
    public String pageSize;

    /**
     * (���M��O��MMF�\���敪)<BR>
     * ���M��O��MMF�\���敪 <BR>
     * <BR>
     * �\���Ώۂ̖������A���M��O��MMF�Ő؂�ւ��邽�߂̋敪 <BR>
     * <BR>
     * 0:���M�̂� <BR>
     * 1:�O��MMF�̂� <BR>
     * 2:���� <BR>
     * <BR>
     * ��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * (�����M�����t�ꗗ�Ɖ�N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A879240065
     */
    public WEB3MutualBuyListRequest()
    {
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M���t�ꗗ�Ɖ�X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A878D10288
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyListResponse(this);
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListRequest.class);
        
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00089 <BR>
     * �@@�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * �Q�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00091 <BR>
     * �@@�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * �R�j�@@�Ɖ�敪�`�F�b�N <BR>
     * �@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *    �h���t�ꗗ�h�A<BR>
     *    �h�ژ_�����X�������ꗗ�h <BR>
     *    �h���t�̂݁h <BR>
     *    �h��W�̂݁h <BR>
     * <BR>
     * @@roseuid 40A878C60249
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�|�P�j�@@this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�P�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����
        try
        {
            Double.parseDouble(this.pageIndex);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�Q�|�P�j�@@this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        
        //�Q�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����
        try
        {
            Double.parseDouble(this.pageSize);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("�y�[�W���\���s���������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�R�j�@@�Ɖ�敪�`�F�b�N 
        //�@@�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B 
        //�h���t�ꗗ�h�A�h�ژ_�����X�������ꗗ�h �h���t�̂݁h �h��W�̂݁h 

        if (!WEB3MFReferenceDivDef.BUY_REFERENCE.equals(this.referenceType) && 
            !WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(this.referenceType) &&
            !WEB3MFReferenceDivDef.BUY.equals(this.referenceType) &&
            !WEB3MFReferenceDivDef.RECRUIT.equals(this.referenceType))
        {
            log.debug("�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@

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
filename	WEB3EquitySellListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�ꗗ���N�G�X�g(WEB3EquitySellListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 �C�ї� (���u) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������t�ꗗ���N�G�X�g�j�B<BR>
 * <BR>
 * ���������ۗL���Y�ꗗ�Ɖ�v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquitySellListRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellListRequest.class);
        
    /**
     * �����R�[�h
     */
    public String productCode;

    /**
     * �s��R�[�h
     */
    public String marketCode;

    /**
     * ���������\�[�g�L�[�̈ꗗ
     * 
     * �Ώۍ��ځF�������ށA�����敪
     */
    public WEB3EquitySortKey[] sortKeys;

    /**
     * �v���y�[�W�ԍ�<BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     */
    public String pageIndex;

    /**
     * �y�[�W���\���s��<BR>
     * �P�y�[�W���ɕ\�����������s��<BR>
     */
    public String pageSize;

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405101030L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_sell_list";

    /**
     * @@roseuid 409F5AB90000
     */
    public WEB3EquitySellListRequest()
    {

    }

    /**
     * ���X�|���X�f�[�^���쐬����B
     * @@return WEB3EquityCommonInputResponse
     * @@roseuid 4063B6D90191
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellListResponse(this);
    }

    /**
     * (validate)<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�P�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�L�[���ڂɍ��ږ��ȊO�̒l�����݁v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�����i�����R�[�h�j<BR>
     * �@@�@@�@@�@@�E�����敪<BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@this.�v���y�[�W�ԍ����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@this.�y�[�W���\���s�����ȉ��̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�ȉ��̗�O���X���[����B<BR>
     * �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�y�[�W���\���s����null�v�̗�O���X���[)<BR>
     * �@@�@@�@@�E�����ȊO�@@(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)<BR>
     * �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)<BR>
     * <BR>
     * �S�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h��null�A<BR>
     * �@@�����L�̒l�ȊO�̏ꍇ�A<BR>
     * �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�P�F����<BR>
     * �@@�@@�@@�E�Q�F���<BR>
     * �@@�@@�@@�E�R�F���É�<BR>
     * �@@�@@�@@�E�U�F����<BR>
     * �@@�@@�@@�E�W�F�D�y<BR>
     * �@@�@@�@@�E�X�FNNM<BR>
     * �@@�@@�@@�E�P�O�FJASDAQ<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4067DC4602FC
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�\�[�g�L�[�̃`�F�b�N");
        // �P�j�\�[�g�L�[�̃`�F�b�N
        // �@@�P�|�P�jthis.�\�[�g�L�[��null�ł������ꍇ
        // �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if(this.sortKeys == null)
        {
            // ��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + ".validate()");
        }
        
        // �@@�P�|�Q�jthis.�\�[�g�L�[.�v�f�����O�������ꍇ
        // �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        int sortKeysCount = this.sortKeys.length;
        if(sortKeysCount == 0)
        {
            // ��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + ".validate()");
        }
        
        // �@@�P�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���
        // �@@�@@�@@�@@���L�̃`�F�b�N���s���B
        for(int i = 0 ; i < sortKeysCount ; i++)
        {
            // �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
            sortKeys[i].validate();
            
            // �@@�@@�P�|�R�|�Q�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�L�[���ڂɍ��ږ��ȊO�̒l�����݁v�̗�O���X���[����B
            // �@@�@@�@@�@@�E�����i�����R�[�h�j
            // �@@�@@�@@�@@�E�����敪<BR>
            if(!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem)
                && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem))
            {
                // ��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.debug("�v���y�[�W�ԍ��`�F�b�N");
        // �Q�j�@@�v���y�[�W�ԍ��`�F�b�N
        // �@@this.�v���y�[�W�ԍ����ȉ��̂����ꂩ�ɊY������ꍇ�́A
        // �@@�ȉ��̗�O���X���[����B
        // �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�v���y�[�W�ԍ���null�v�̗�O���X���[)
        // �@@�@@�@@�E�����ȊO�@@(�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[)
        // �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[)
        if(this.pageIndex == null)
        {
            // �v���y�[�W�ԍ���null�̏ꍇ
            // ��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageIndex= Integer.parseInt(this.pageIndex);
            if(l_intPageIndex <= 0)
            {
                // �v���y�[�W�ԍ����O�ȉ��̏ꍇ
                // ��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // ��O
            // �v���y�[�W�ԍ��������ȊO�̏ꍇ
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + ".validate()");
        }

        // �R�j�@@�y�[�W���\���s���`�F�b�N
        // �@@this.�y�[�W���\���s�����ȉ��̂����ꂩ�ɊY������ꍇ�́A
        // �@@�ȉ��̗�O���X���[����B
        // �@@�@@�@@�Enull�@@�@@�@@�@@�@@(�u�y�[�W���\���s����null�v�̗�O���X���[)
        // �@@�@@�@@�E�����ȊO�@@(�u�y�[�W���\���s���������ȊO�v�̗�O���X���[)
        // �@@�@@�@@�E���O�@@�@@�@@�@@ (�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[)
        if(this.pageSize == null)
        {
            // �y�[�W���\���s����null�̏ꍇ
            // ��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + ".validate()");
        }
        try
        {
            int l_intPageSize= Integer.parseInt(this.pageSize);
            if(l_intPageSize <= 0)
            {
                // �y�[�W���\���s�����O�ȉ��̏ꍇ
                // ��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + ".validate()");
            }
        }catch(NumberFormatException e)
        {
            // �y�[�W���\���s���������ȊO�̏ꍇ
            // ��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + ".validate()");
        }

        log.debug("�s��R�[�h�`�F�b�N");
        // �S�j�@@�s��R�[�h�`�F�b�N
        // �@@this.�s��R�[�h��null�A
        // �@@�����L�̒l�ȊO�̏ꍇ�A
        // �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E�P�F����
        // �@@�@@�@@�E�Q�F���
        // �@@�@@�@@�E�R�F���É�
        // �@@�@@�@@�E�U�F����
        // �@@�@@�@@�E�W�F�D�y
        // �@@�@@�@@�E�X�FNNM
        // �@@�@@�@@�E�P�O�FJASDAQ
        if(this.marketCode != null)
        {
            if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
            {
                // ��O
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + ".validate()");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@

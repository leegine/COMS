head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeAccOpenDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݋敪(WEB3GentradeAccOpenDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/16 ��іQ(���u) �V�K�쐬 ���f��No.339,341,342,343,344,345
*/

package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.gentrade.data.AccOpenDivDao;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�݋敪)<BR>
 * �����J�݋敪�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3GentradeAccOpenDiv implements BusinessObject
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeAccOpenDiv.class);

    /**
     * (�����J�݋敪�s)<BR>
     * �����J�݋敪�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �����J�݋敪Params�N���X��DDL��莩����������B<BR>
     * �� DB���C�A�E�g�u�����J�݋敪�e�[�u���d�l.xls�v�Q�ƁB<BR>
     */
    private AccOpenDivParams accOpenDivParams;

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�����J�݋敪Params��ԋp����B<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.accOpenDivParams;
    }

    /**
     * (�����J�݋敪)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����J�݋敪�I�u�W�F�N�g�𐶐�����B<BR>
     */
    public WEB3GentradeAccOpenDiv()
    {
        this.accOpenDivParams = new AccOpenDivParams();
    }

    /**
     * (�����J�݋敪)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̏����Ɉ�v��������J�݋敪�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�����̒l���L�[�Ƃ��Č����J�݋敪�e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�����J�݋敪Row�j�� this.�����J�݋敪Row�ɃZ�b�g����B<BR>
     * <BR>
     * ���f�[�^���擾�o���Ȃ������ꍇ��null���Z�b�g����B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strAccType - (�������)<BR>
     * �������<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeAccOpenDiv(long l_lngAccountId, String l_strAccType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeAccOpenDiv(long, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@DB����
        //�����̒l���L�[�Ƃ��Č����J�݋敪�e�[�u������������B
        AccOpenDivRow l_accOpenDivRow = null;

        try
        {
            l_accOpenDivRow =
                AccOpenDivDao.findRowByAccountIdAccType(l_lngAccountId, l_strAccType);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�������ʂ̍s�I�u�W�F�N�g�i�����J�݋敪Row�j�� this.�����J�݋敪Row�ɃZ�b�g����B
        //�f�[�^���擾�o���Ȃ������ꍇ��null���Z�b�g����B
        if (l_accOpenDivRow != null)
        {
            this.accOpenDivParams = new AccOpenDivParams(l_accOpenDivRow);
        }
        else
        {
            this.accOpenDivParams = null;
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����J�݋敪)<BR>
     * this.�����J�݋敪�s.�����J�݋敪��ԋp����B<BR>
     * <BR>
     * ��this.�����J�݋敪�s��null�̏ꍇ�́A�h0:���J�݁h��ԋp����B<BR>
     * @@return String
     */
    public String getAccOpenDiv()
    {
        final String STR_METHOD_NAME = "getAccOpenDiv()";
        log.entering(STR_METHOD_NAME);

        //this.�����J�݋敪�s��null�̏ꍇ�́A�h0:���J�݁h��ԋp����B
        if (this.accOpenDivParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenDef.NOT_OPEN;
        }

        log.exiting(STR_METHOD_NAME);
        return this.accOpenDivParams.getAccOpenDiv();
    }

    /**
     * (insert�����J�݋敪)<BR>
     * �����̓��e�Ō����J�݋敪�e�[�u��(acc_open_div)�ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �uFX�����J��_�����J�݋敪�e�[�u��.xls�v<BR>
     * ((FX�����J��)�����J�݋敪�e�[�u��_DB�X�V�d�l)<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strAccType - (�������)<BR>
     * �������<BR>
     * @@param l_strAccOpenDiv - (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * @@param l_strLastUpdater - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void insertAccOpenDiv(
        long l_lngAccountId, String l_strAccType, String l_strAccOpenDiv, String l_strLastUpdater)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "insertAccOpenDiv(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        AccOpenDivParams l_accOpenDivParams = new AccOpenDivParams();

        //����.����ID
        l_accOpenDivParams.setAccountId(l_lngAccountId);

        //����.�������
        l_accOpenDivParams.setAccType(l_strAccType);

        //����.�����J�݋敪
        l_accOpenDivParams.setAccOpenDiv(l_strAccOpenDiv);

        //��������(YYYY/MM/DD 00:00:00)
        Date l_datProcessDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        l_accOpenDivParams.setAccOpenDate(l_datProcessDate);

        //����.�X�V�҃R�[�h
        l_accOpenDivParams.setLastUpdater(l_strLastUpdater);

        //���ݎ���
        l_accOpenDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //���ݎ���
        l_accOpenDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_accOpenDivParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�����J�݋敪)<BR>
     * �����J�݋敪�e�[�u��.�����J�݋敪��update����B<BR>
     * <BR>
     * �X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V<BR>
     * �u�Ǘ��ҁE�����Ǘ�_�����J�݋敪�e�[�u��DB�X�V�d�l�v���Q��<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strAccType - (�������)<BR>
     * �������<BR>
     * @@param l_strAccOpenDiv - (�����J�݋敪)<BR>
     * �����J�݋敪<BR>
     * @@param l_strLastUpdater - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void updateAccOpenDiv(
        long l_lngAccountId, String l_strAccType, String l_strAccOpenDiv, String l_strLastUpdater)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAccOpenDiv(long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            AccOpenDivRow l_accOpenDivRow =
                AccOpenDivDao.findRowByPk(l_lngAccountId, l_strAccType);

            AccOpenDivParams l_accOpenDivParams = new AccOpenDivParams(l_accOpenDivRow);

            //����.�����J�݋敪
            l_accOpenDivParams.setAccOpenDiv(l_strAccOpenDiv);

            //����.�X�V�҃R�[�h
            l_accOpenDivParams.setLastUpdater(l_strLastUpdater);

            //���ݎ���
            l_accOpenDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_accOpenDivParams);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
}
@

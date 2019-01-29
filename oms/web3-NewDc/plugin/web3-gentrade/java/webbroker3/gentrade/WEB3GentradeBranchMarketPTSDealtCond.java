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
filename	WEB3GentradeBranchMarketPTSDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i���X�s��ʁEPTS�j�戵����(WEB3GentradeBranchMarketPTSDealtCond.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 �����q (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondDao;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �،���ЁA���X�A�s�ꖈ�̎戵�\����������\������B<BR>
 * <BR>
 * �iDB���C�A�E�g �u�i���X�s��ʁEPTS�j�戵�����e�[�u��.xls�v�Q�Ɓj<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3GentradeBranchMarketPTSDealtCond implements BusinessObject
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketPTSDealtCond.class);

    /**
     * (�i���X�s��ʁEPTS�j�戵����)
     */
    private BranchMarketPtsDealtCondRow branchMarketPTSDealtCondRow;

    /**
     * this.�i���X�s��ʁEPTS�j�戵����Row��ԋp����B<BR>
     * <BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketPTSDealtCondRow;
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �����̏����Ɉ�v����i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�����̒l�ɂāi���X�s��ʁEPTS�j�戵�����e�[�u������������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g<BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�s��ʁEPTS�j�戵����Row�j��<BR>
     * �@@this.�i���X�s��ʁEPTS�j�戵�����ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeBranchMarketPTSDealtCond(String l_strInstitutionCode,
        String l_strBranchCode, String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchMarketPTSDealtCond(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@DB����
        // �����̒l�ɂāi���X�s��ʁEPTS�j�戵�����e�[�u������������B
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        try
        {
            l_branchMarketPtsDealtCondRow = BranchMarketPtsDealtCondDao.findRowByPk(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strMarketCode);
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

        this.branchMarketPTSDealtCondRow = l_branchMarketPtsDealtCondRow;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A<BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_branchMarketPTSDealtCondRow - (�i���X�s��ʁEPTS�j�戵����Row)<BR>
     * �i���X�s��ʁEPTS�j�戵����Row<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeBranchMarketPTSDealtCond(BranchMarketPtsDealtCondRow l_branchMarketPTSDealtCondRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchMarketPTSDealtCond(BranchMarketPTSDealtCondRow)";
        log.entering(STR_METHOD_NAME);

        // �{�I�u�W�F�N�g���C���X�^���X�����A
        // �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B
        if (l_branchMarketPTSDealtCondRow == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        this.branchMarketPTSDealtCondRow = l_branchMarketPTSDealtCondRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�戵�\)<BR>
     * �w�菤�i���戵�\�ł��邩��ԋp����B<BR>
     * <BR>
     * this.�i���X�s��ʁEPTS�j�戵����Row�̃v���p�e�B�u�戵�\�v���A<BR>
     * �h�戵�\�h�ł����true�A�ȊOfalse��ԋp����B<BR>
     * <BR>
     * �����^�C�v��ProductTypeEnum.�����ȊO�̏ꍇ<BR>
     * ��O��throw����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3SystemLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@SYSTEM_ERROR_80017<BR>
     * <BR>
     * @@param l_productTypeEnum - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isHandlingPossible(ProductTypeEnum l_productTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isHandlingPossible(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // �����^�C�v��ProductTypeEnum.�����ȊO�̏ꍇ
        if (!ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            // ��O��throw����B
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // this.�i���X�s��ʁEPTS�j�戵����Row�̃v���p�e�B�u�戵�\�v��
        // �h�戵�\�h�ł����true�A�ȊOfalse��ԋp����B
        String l_strMartcanDealtEquity = this.branchMarketPTSDealtCondRow.getMartCanDealtEquity();
        if (WEB3DealtDef.CAN_DEALT.equals(l_strMartcanDealtEquity))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�i���X�s��ʁEPTS�j�戵����)<BR>
     * �����̕��X�ɊY������i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * ���s��R�[�h�����Ŏ擾����B<BR>
     * <BR>
     * �P�j�@@�i���X�s��ʁEPTS�j�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()<BR>
     * �@@���X�R�[�h = ���X.getBranchCode()<BR>
     * �@@���s��R�[�h�����Ń\�[�g���Ď擾����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ́i���X�s��ʁEPTS�j�戵�����s�I�u�W�F�N�g���Ɉȉ��������s���B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���������I�u�W�F�N�g��z��ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�Q�j�Ő��������z���ԋp����B<BR>
     * <BR>
     * @@param l_gentradeBranch - (���X)<BR>
     * ���X<BR>
     * @@return WEB3GentradeBranchMarketPTSDealtCond[]
     * @@throws WEB3BaseException
     */
    public static WEB3GentradeBranchMarketPTSDealtCond[] getBranchMarketPTSDealtCond(
        WEB3GentradeBranch l_gentradeBranch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketPTSDealtCond(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�i���X�s��ʁEPTS�j�戵�����e�[�u�����ȉ��̏����Ō�������B
        // [����]
        // �،���ЃR�[�h = ���X.getInstitution().getInstitutionCode()
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? and ");
        // ���X�R�[�h = ���X.getBranchCode()
        l_sbWhere.append(" branch_code = ? ");

        // ���X.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_gentradeBranch.getInstitution().getInstitutionCode();
        // ���X.getBranchCode()
        String l_strBranchCode = l_gentradeBranch.getBranchCode();
        Object[] l_objWheres = {l_strInstitutionCode, l_strBranchCode};

        List l_lisRecords = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lisRecords = processor.doFindAllQuery(
                BranchMarketPtsDealtCondRow.TYPE,
                l_sbWhere.toString(),
                " market_code asc ",
                null,
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �Q�j�@@�������ʂ́i���X�s��ʁEPTS�j�戵�����s�I�u�W�F�N�g���Ɉȉ��������s���B
        // �Q�|�P�j�@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�𐶐�����B
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            new WEB3GentradeBranchMarketPTSDealtCond[l_lisRecords.size()];
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            l_branchMarketPtsDealtCondRow = (BranchMarketPtsDealtCondRow) l_lisRecords.get(i);
            l_branchMarketPTSDealtCond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondRow);
            l_branchMarketPTSDealtConds[i] = l_branchMarketPTSDealtCond;
        }
        
        // �R�j�@@�Q�j�Ő��������z���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_branchMarketPTSDealtConds;
    }

    /**
     * (get�i���X�s��ʁEPTS�j�戵����)<BR>
     * �����̏،���ЃR�[�h�ɊY������<BR>
     * �i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * ���s��R�[�h�����Ŏ擾����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�i���X�s��ʁEPTS�j�戵�����e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@���s��R�[�h�����Ń\�[�g���Ď擾����B<BR>
     * <BR>
     * �Q�j�I�u�W�F�N�g����<BR>
     * �@@�������ʂ́i���X�s��ʁEPTS�j�戵�����s�I�u�W�F�N�g���w�肵�A<BR>
     * �@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@���������I�u�W�F�N�g��z��ŕԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return WEB3GentradeBranchMarketPTSDealtCond[]
     * @@exception WEB3BaseException
     */
    public static WEB3GentradeBranchMarketPTSDealtCond[] getBranchMarketPTSDealtCond(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketPTSDealtCond(String)";
        log.entering(STR_METHOD_NAME);

        // �P�jDB����
        // �@@�i���X�s��ʁEPTS�j�戵�����e�[�u�����ȉ��̏����Ō�������B
        // [����]
        // �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h
        //���s��R�[�h�����Ń\�[�g���Ď擾����B
        List l_lisRecords = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lisRecords = processor.doFindAllQuery(
                BranchMarketPtsDealtCondRow.TYPE,
                " institution_code = ? ",
                " market_code asc ",
                null,
                new Object[]{l_strInstitutionCode});
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �Q�j�I�u�W�F�N�g����
        // �������ʂ́i���X�s��ʁEPTS�j�戵�����s�I�u�W�F�N�g���w�肵�A
        // �i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g�𐶐�����B
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            new WEB3GentradeBranchMarketPTSDealtCond[l_lisRecords.size()];
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            l_branchMarketPtsDealtCondRow = (BranchMarketPtsDealtCondRow) l_lisRecords.get(i);
            l_branchMarketPTSDealtCond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondRow);
            l_branchMarketPTSDealtConds[i] = l_branchMarketPTSDealtCond;
        }

        // ���������I�u�W�F�N�g��z��ŕԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_branchMarketPTSDealtConds;
    }

    /**
     * (get�s��R�[�h)<BR>
     * �{�I�u�W�F�N�g���ێ�����s��R�[�h���擾����B<BR>
     * <BR>
     * this.�i���X�s��ʁEPTS�j�戵����Row.�s��R�[�h��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getMarketCode()
    {
        return this.branchMarketPTSDealtCondRow.getMarketCode();
    }

    /**
     * (get�戵�\�s��)<BR>
     * �����̕��X�ɊY������i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�݂̂�<BR>
     * ArrayList�ɐݒ肵�ĕԂ��B<BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B<BR>
     * <BR>
     * �P�j�f�[�^�擾<BR>
     * �@@this.get�i���X�s��ʁEPTS�j�戵����(���X)�ɂ��A<BR>
     * �@@�����̕��X�ɊY������i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N<BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�`�F�b�N���e�F<BR>
     * �@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�쐬����ArrayList��Ԃ��B<BR>
     * <BR>
     * @@param l_genBranch - (���X)<BR>
     * ���X<BR>
     * @@param l_productTypeEnum - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // �f�[�^�擾
        // this.get�i���X�s��ʁEPTS�j�戵����(���X)�ɂ��A
        // �����̕��X�ɊY������i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_genBranch);

        // ArrayList�ɐݒ肵�ĕԂ��B
        ArrayList l_lisRecords = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // �Q�j�戵�\�`�F�b�N
            // �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B
            // �`�F�b�N���e�F
            // this.is�戵�\(�����^�C�v)==true�̏ꍇ�A
            if (l_branchMarketPTSDealtConds[i].isHandlingPossible(l_productTypeEnum))
            {
                // ���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
                String l_strMarketCode =
                    l_branchMarketPTSDealtConds[i].getMarketCode();
                if(!l_lisRecords.contains(l_strMarketCode))
                {
                    l_lisRecords.add(l_strMarketCode);
                }
            }
        }

        // �R�j�쐬����ArrayList��Ԃ��B�B
        String[] l_strHandlingPossibleMarkets =
            new String[l_lisRecords.size()];
        l_lisRecords.toArray(l_strHandlingPossibleMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get�戵�\�s��)<BR>
     * �����̏،���ЃR�[�h�ɊY������<BR>
     * �i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾���A<BR>
     * �����̖����^�C�v�ɊY������I�u�W�F�N�g�̎s��R�[�h�݂̂�<BR>
     * ArrayList�ɐݒ肵�ĕԂ��B<BR>
     * ArrayList�ւ̐ݒ�́A�s��R�[�h�����Ƃ���B<BR>
     * <BR>
     * �P�j�f�[�^�擾<BR>
     * �@@this.get�i���X�s��ʁEPTS�j�戵����(�،���ЃR�[�h)�ɂ��A<BR>
     * �@@�����̏،���ЃR�[�h�ɊY������<BR>
     * �@@�i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �Q�j�戵�\�`�F�b�N<BR>
     * �@@�P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�`�F�b�N���e�F<BR>
     * �@@this.is�戵�\(�����^�C�v)==true�̏ꍇ�A<BR>
     * �@@���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B<BR>
     * �@@���������A���Y���R�[�h�̎s��R�[�h������ArrayList�ɑ��݂���ꍇ�́A<BR>
     * �@@�@@�ǉ����Ȃ��B<BR>
     * <BR>
     * �R�j�쐬����ArrayList.toArray()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_productTypeEnum - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        // �f�[�^�擾
        // this.get�i���X�s��ʁEPTS�j�戵����(�،���ЃR�[�h)�ɂ��A
        // �����̏،���ЃR�[�h�ɊY������i���X�s��ʁEPTS�j�戵�����I�u�W�F�N�g��S�Ď擾����B
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_strInstitutionCode);

        // ArrayList�ɐݒ肵�ĕԂ��B
        ArrayList l_lisRecords = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // �Q�j�戵�\�`�F�b�N
            // �P�j�Ŏ擾�����I�u�W�F�N�g�����A�ȉ��̃`�F�b�N���s���B
            // �`�F�b�N���e�F
            // this.is�戵�\(�����^�C�v)==true�̏ꍇ�A
            if (l_branchMarketPTSDealtConds[i].isHandlingPossible(l_productTypeEnum))
            {
                // ���Y���R�[�h�̎s��R�[�h��ArrayList�ɒǉ�����B
                String l_strMarketCode =
                    l_branchMarketPTSDealtConds[i].getMarketCode();
                if (!l_lisRecords.contains(l_strMarketCode))
                {
                    l_lisRecords.add(l_strMarketCode);
                }
            }
        }

        // �R�j�쐬����ArrayList��Ԃ��B
        String[] l_strHandlingPossibleMarkets =
            new String[l_lisRecords.size()];
        l_lisRecords.toArray(l_strHandlingPossibleMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get����\������z)<BR>
     * �{�I�u�W�F�N�g���ێ��������\���z����l���擾����B<BR>
     * <BR>
     * this.�i���X�s��ʁEPTS�j�戵����Row.����\���z����l��ԋp����B<BR>
     * <BR>
     * @@return double
     */
    public double getMaxHandlingPrice()
    {
        return this.branchMarketPTSDealtCondRow.getMaxHandlingPrice();
    }

    /**
     * (get�������x�P��)<BR>
     * �{�I�u�W�F�N�g���ێ����锄�����x�P�ʂ��擾����B<BR>
     * <BR>
     * this.�i���X�s��ʁEPTS�j�戵����Row.�������x�P�ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     */
    public double getLimitedUnit()
    {
        return this.branchMarketPTSDealtCondRow.getLimitedUnit();
    }
}
@

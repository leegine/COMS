head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����@@����(WEB3AdminFPTCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 �����q (���u) �V�K�쐬
Revision History : 2007/10/09 �����q (���u) ���f��No.002
Revision History : 2007/12/07 ���g (���u) ���f��No.013
Revision History : 2008/01/28 ���g (���u) ���f��No.023,No.028
*/

package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.define.WEB3AdminFPTDocumentCheckDivDef;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����@@����)<BR>
 * �����@@���ʃN���X<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminFPTCommon
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTCommon.class);

    /**
     * (�����@@����)<BR>
     * �f�B�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 46F8DA35024D
     */
    public WEB3AdminFPTCommon()
    {

    }

    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@����.���ʃ`�F�b�N�敪 == IPO�̏ꍇ<BR>
     * <BR>
     *   �P-�P�j�@@this.getIPO������()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h�F����.�،����.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@�@@�@@�@@�����R�[�h�F����.�����R�[�h<BR>
     * <BR>
     *   �P-�Q�j�@@this.getIPO������()�̖߂�l != (null or "")�̏ꍇ�A<BR>
     * �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     *   �P-�R�j�@@this.getIPO������()�̖߂�l == (null or "") �̏ꍇ�A<BR>
     * �@@�@@""��ԋp����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@����.���ʃ`�F�b�N�敪 == ���M�̏ꍇ<BR>
     * <BR>
     *   �Q-�P�j�@@this.get���M������()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЁF����.�،����<BR>
     * �@@�@@�@@�@@�����R�[�h�F����.�����R�[�h<BR>
     * <BR>
     *   �Q-�Q�j�@@this.get���M������()�̖߂�l != (null or "") �̏ꍇ�A<BR>
     *   �@@�@@�߂�l��ԋp����B<BR>
     * <BR>
     *   �Q-�R�j�@@this.get���M������()�̖߂�l == (null or "") �̏ꍇ�A"" ��ԋp����B<BR>
     * <BR>
     * <BR>
     * �R�j�@@����.���ʃ`�F�b�N�敪 == FX�̏ꍇ�A"" ��ԋp����B<BR>
     * <BR>
     * <BR>
     * �S�j�@@����.���ʃ`�F�b�N�敪 == �����@@�̏ꍇ�A"" ��ԋp����B<BR>
     * <BR>
     * �T�j�@@����.���ʃ`�F�b�N�敪����L�ȊO�̏ꍇ�A"" ��ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strDocumentCheckDiv - (���ʃ`�F�b�N�敪)<BR>
     * ���ʃ`�F�b�N�敪<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F3A07A0085
     */
    public String getProductName(
        Institution l_institution,
        String l_strProductCode,
        String l_strDocumentCheckDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductName(WEB3GentradeInstitution ,String , String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        // �P�j ����.���ʃ`�F�b�N�敪 == IPO�̏ꍇ
        if (WEB3AdminFPTDocumentCheckDivDef.IPO.equals(l_strDocumentCheckDiv))
        {
            //  �P-�P�j this.getIPO������()���R�[������B
            // �،���ЃR�[�h�F����.�،����.get�،���ЃR�[�h()�̖߂�l
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            String l_strIPOProductName = this.getIPOStandardName(l_strInstitutionCode, l_strProductCode);

            if (WEB3StringTypeUtility.isNotEmpty(l_strIPOProductName))
            {
                // �P-�Q�j this.getIPO������()�̖߂�l != (null or "") �̏ꍇ�A�߂�l��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_strIPOProductName;
            }
            else
            {
                // �P-�R�j this.getIPO������()�̖߂�l == (null or "") �̏ꍇ�A"" ��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return "";
            }
        }
        else if (WEB3AdminFPTDocumentCheckDivDef.MUTUAL_FUND.equals(l_strDocumentCheckDiv))
        {
            // �Q�j ����.���ʃ`�F�b�N�敪 == ���M�̏ꍇ
            // �Q-�P�j this.get���M������()���R�[������
            String l_strStandardProductName =
                this.getMFProductName(l_institution, l_strProductCode);

            if (WEB3StringTypeUtility.isNotEmpty(l_strStandardProductName))
            {
                // �Q-�Q�j this.get���M������()�̖߂�l != (null or "") �̏ꍇ�A�߂�l��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_strStandardProductName;
            }
            else
            {
                // �Q-�R�j this.get���M������()�̖߂�l == (null or "") �̏ꍇ�A"" ��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return "";
            }
        }
        else if (WEB3AdminFPTDocumentCheckDivDef.FX.equals(l_strDocumentCheckDiv))
        {
            // �R�j ����.���ʃ`�F�b�N�敪 == FX�̏ꍇ�A"" ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return "";
        }

        else if (WEB3AdminFPTDocumentCheckDivDef.FPT.equals(l_strDocumentCheckDiv))
        {
            // �S�j ����.���ʃ`�F�b�N�敪 == �����@@�̏ꍇ�A"" ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return "";
        }
        else
        {
            // �T�j ����.���ʃ`�F�b�N�敪����L�ȊO�̏ꍇ�A"" ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return "";
        }
    }

    /**
     * (getIPO������)<BR>
     * IPO�����e�[�u�������������擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@IPO�����e�[�u�����ȉ������Ō������s���B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�����R�[�h = ����.�����R�[�h<BR>
     * �@@�@@�폜�t���O = 0<BR>
     * �@@�@@�쐬���� �̍~���Ń\�[�g<BR>
     * <BR>
     * �@@�@@�쐬���� = ��L������S�Ė��������R�[�h���ň�ԐV�������t<BR>
     * <BR>
     * �Q�j�@@�P�j�@@�̌�������0�Ԗڂ̃��R�[�h�̖��������擾���A�ԋp����B<BR>
     * <BR>
     * �R�j�@@�P�j�@@�Ō������ʂ�0�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F8A913001E
     */
    private String getIPOStandardName(String l_strInstitutionCode, String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getIPOStandardName(String, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j IPO�����e�[�u�����ȉ������Ō������s���B
        List l_lisRows = null;
        try
        {
            // IPO�����e�[�u�����ȉ������Ō������s���B
            String l_strWhereClause =
                " institution_code = ? and product_code = ? and delete_flag = ? ";

     	    //[����]
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            //�����R�[�h = ����.�����R�[�h
            //�폜�t���O = 0
            //�쐬���� �̍~���Ń\�[�g
            Object[] l_objBindVars = {l_strInstitutionCode, l_strProductCode, BooleanEnum.FALSE};
            String l_strSortCond = " created_timestamp desc ";

            // �ȉ������ŏ��ʋ敪�Ǘ��e�[�u����茟�����s���B
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IpoProductRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_objBindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //  �R�j�P�j �Ō������ʂ�0�̏ꍇ�Anull��ԋp����B
        int l_intSize = l_lisRows.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        // �Q�j �P�j �̌�������0�Ԗڂ̃��R�[�h�̖��������擾���A�ԋp����B
        else
        {
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_lisRows.get(0);
            String l_strIPOProductName = l_ipoProductRow.getStandardName();

            log.exiting(STR_METHOD_NAME);
            return l_strIPOProductName;
        }
    }

    /**
     * (get���M������)<BR>
     * �v���_�N�g�}�l�[�W���𗘗p���A���M���������擾����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���M�̃g���[�f�B���O���W���[�����擾����B<BR>
     * <BR>
     * �Q�j�@@�v���_�N�g�}�l�[�W�����擾����B<BR>
     * <BR>
     * �R�j �v���_�N�g�}�l�[�W��.get���M����()���R�[������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЁF ����.�،����<BR>
     * �@@�@@�@@�����R�[�h�F ����.�����R�[�h<BR>
     * <BR>
     * �S�j�@@�����I�u�W�F�N�g���擾�ł����ꍇ�A<BR>
     * �@@�@@�@@�����I�u�W�F�N�g.getDataSourceObject().get������()��ԋp����B<BR>
     * <BR>
     * �T�j�@@�����I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F8AD2701BF
     */
    private String getMFProductName(
        Institution l_institution, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMFProductName(Institution, String)";
        log.entering(STR_METHOD_NAME);

        // �v���_�N�g�}�l�[�W���𗘗p���A���M���������擾����B
        // �P�j ���M�̃g���[�f�B���O���W���[�����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // �Q�j �v���_�N�g�}�l�[�W�����擾����B
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        // �R�j �v���_�N�g�}�l�[�W��.get���M����()���R�[������B
        // �،���ЁF ����.�،����
        // �����R�[�h�F ����.�����R�[�h
        MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                l_productManager.getMutualFundProduct(l_institution, l_strProductCode);

            // �S�j �����I�u�W�F�N�g���擾�ł����ꍇ�A
            // �����I�u�W�F�N�g.getDataSourceObject().get������()��ԋp����B
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            String l_strStandardName = l_mutualFundProductRow.getStandardName();

            log.exiting(STR_METHOD_NAME);
            return l_strStandardName;
        }
        catch (NotFoundException l_ex)
        {
            // �T�j �����I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get�V�X�e���v���t�@@�����X)<BR>
     * �V�X�e���v���t�@@�����X��背�R�[�h���擾����B<BR>
     * <BR>
     * �P�j ����.���ϐ����������Ɍ������s���B<BR>
     * <BR>
     * �Q�j ���R�[�h���擾�ł����ꍇ�A�l��ԋp����B<BR>
     * <BR>
     * �R�j ���R�[�h���擾�ł��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * @@param l_strPreference - (���ϐ���)<BR>
     * ���ϐ���<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getSystemPreferences(String l_strPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSystemPreferences(String)";
        log.entering(STR_METHOD_NAME);

        SystemPreferencesRow l_systemPreferencerow;
        try
        {
            //�P�j ����.���ϐ����������Ɍ������s���B
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_strPreference);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_systemPreferencerow != null)
        {
            //�Q�j ���R�[�h���擾�ł����ꍇ�A�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_systemPreferencerow.getValue();
        }
        else
        {
            //�R�j ���R�[�h���擾�ł��Ȃ��ꍇ�́Anull��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get���ʎ�ޏڍ׏��)<BR>
     * ���ʎ�ފǗ����A�y�ёΉ�����d�q�������R�[�h�Ǘ������擾���A<BR>
     * ���ʎ�ޏڍ׏����쐬����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���ʋ敪�Ǘ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�@@���ʋ敪 = ����.���ʋ敪<BR>
     * �@@�@@�@@���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h<BR>
     * <BR>
     * <BR>
     * �Q�j�@@���ʋ敪�Ǘ������擾����B<BR>
     * �@@�@@�@@���ʋ敪�Ǘ�#get���ʋ敪�Ǘ��ꗗ()���R�[������B<BR>
     * <BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g�̐���<BR>
     * <BR>
     * <BR>
     * �S�j�@@�Q�j�@@�Ŏ擾�������ʋ敪�Ǘ����z��̒������ALoop����<BR>
     * �@@�S-�P�j�@@�d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�@@�@@���ʋ敪 = ���ʋ敪�Ǘ����[index].���ʋ敪<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h = ���ʋ敪�Ǘ����[index].���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�S-�Q�j�@@�d�q�������R�[�h�Ǘ������擾����B<BR>
     * �@@�@@�@@�d�q�������R�[�h�Ǘ�#�d�q�������R�[�h�Ǘ��ꗗ()���R�[������B<BR>
     * �@@�@@�@@�߂�l�z��̒��� == 0 �̏ꍇ�́A�u�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02999<BR>
     * <BR>
     * <BR>
     * �@@�S-�R�j�@@���ʎ�ޏڍ׏��I�u�W�F�N�g�𐶐����A�ȉ����Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@���ʋ敪�Ǘ���� = ���ʋ敪�Ǘ����[index]<BR>
     * �@@�@@�@@�@@�d�q�������R�[�h�Ǘ���� = �d�q�������R�[�h�Ǘ�#�d�q�������R�[�h�Ǘ��ꗗ()�̖߂�l<BR>
     * <BR>
     * �@@�S-�S�j�@@���ʎ�ޏڍ׏��I�u�W�F�N�g��List��add()<BR>
     * <BR>
     * <BR>
     * �T�j�@@ArrayList�I�u�W�F�N�g�����ʎ�ޏڍ׏��[]�ɕϊ�<BR>
     * <BR>
     * <BR>
     * �U�j�@@�T�j�ŕϊ������l��return<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strDocumentDiv - (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     * @@param l_strDocumentCategory - (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     * @@return WEB3FPTDocumentCategoryDetailsInfoUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3FPTDocumentCategoryDetailsInfoUnit[] getDocumentCategoryDetailsInfoUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocumentDiv,
        String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentCategoryDetailsInfoUnit(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ���ʋ敪�Ǘ��I�u�W�F�N�g�𐶐�����B
        //�،���ЃR�[�h = ����.�،���ЃR�[�h
        //���X�R�[�h = ����.���X�R�[�h
        //���ʋ敪 = ����.���ʋ敪
        //���ʎ�ރR�[�h = ����.���ʎ�ރR�[�h
        WEB3AdminFPTDocDivManagement l_docDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strDocumentDiv,
                l_strDocumentCategory);

        //�Q�j ���ʋ敪�Ǘ������擾����B
        //���ʋ敪�Ǘ�#get���ʋ敪�Ǘ��ꗗ()���R�[������B
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivAdminInfoUnits =
            l_docDivManagement.getDocDivManagementLists();

        //�R�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisDocumentCategoryDetailsInfoUnits = new ArrayList();

        //�S�j �Q�j �Ŏ擾�������ʋ敪�Ǘ����z��̒������ALoop����
        for (int i = 0; i < l_documentDivAdminInfoUnits.length; i++)
        {
            //�S-�P�j �d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B
            //�،���ЃR�[�h = ����.�،���ЃR�[�h
            //���X�R�[�h = ����.���X�R�[�h
            //���ʋ敪 = ���ʋ敪�Ǘ����[index].���ʋ敪
            //�d�q�������R�[�h = ���ʋ敪�Ǘ����[index].���ʎ�ރR�[�h
            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_documentDivAdminInfoUnits[i].documentDiv,
                    l_documentDivAdminInfoUnits[i].documentCategory);

            //�S-�Q�j �d�q�������R�[�h�Ǘ������擾����B
            //�d�q�������R�[�h�Ǘ�#�d�q�������R�[�h�Ǘ��ꗗ()���R�[������B
            //�߂�l�z��̒��� == 0 �̏ꍇ�́A�u�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���v��O���X���[����B
            WEB3FPTBatoProductCodeAdminInfoUnit[] l_batoProductCodeAdminInfoUnits =
                l_batoProductCodeManagement.getBatoProductCodeAdminInfoUnit();
            int l_intBatoProductCodeAdminInfoUnit = l_batoProductCodeAdminInfoUnits.length;
            if (l_intBatoProductCodeAdminInfoUnit == 0)
            {
                log.debug("�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                    WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                    "�d�q�������R�[�h�Ǘ��e�[�u���Ƀ��R�[�h�����݂��܂���B");
            }

            //�S-�R�j ���ʎ�ޏڍ׏��I�u�W�F�N�g�𐶐����A�ȉ����Z�b�g����B
            WEB3FPTDocumentCategoryDetailsInfoUnit l_documentCategoryDetailsInfoUnit =
                new WEB3FPTDocumentCategoryDetailsInfoUnit();

            //���ʋ敪�Ǘ���� = ���ʋ敪�Ǘ����[index]
            l_documentCategoryDetailsInfoUnit.documentDivList = l_documentDivAdminInfoUnits[i];

            //�d�q�������R�[�h�Ǘ���� = �d�q�������R�[�h�Ǘ�#�d�q�������R�[�h�Ǘ��ꗗ()�̖߂�l
            l_documentCategoryDetailsInfoUnit.batoProductCodeAdminInfo = l_batoProductCodeAdminInfoUnits;

            //�S-�S�j�@@���ʎ�ޏڍ׏��I�u�W�F�N�g��List��add()
            l_lisDocumentCategoryDetailsInfoUnits.add(l_documentCategoryDetailsInfoUnit);
        }

        //�T�j�@@ArrayList�I�u�W�F�N�g�����ʎ�ޏڍ׏��[]�ɕϊ�
        WEB3FPTDocumentCategoryDetailsInfoUnit[] l_documentCategoryDetailsInfoUnits =
            new WEB3FPTDocumentCategoryDetailsInfoUnit[l_lisDocumentCategoryDetailsInfoUnits.size()];

        l_lisDocumentCategoryDetailsInfoUnits.toArray(l_documentCategoryDetailsInfoUnits);

        //�U�j�@@�T�j�ŕϊ������l��return
        log.exiting(STR_METHOD_NAME);
        return l_documentCategoryDetailsInfoUnits;
    }
}
@

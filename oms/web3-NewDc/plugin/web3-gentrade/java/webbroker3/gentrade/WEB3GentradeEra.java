head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeEra.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �N��(WEB3GentradeEra.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/13 ��іQ(���u) �V�K�쐬 ���f��No.337
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3EraBornDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.data.EraRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�N��)<BR>
 * �N����\���N���X�B<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3GentradeEra
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeEra.class);

    /**
     * �N����\���N���X
     */
    public WEB3GentradeEra()
    {

    }

    /**
     * �����̔N���A�a�����𐼗�̓��t�^�ɕϊ�����B<BR>
     * <BR>
     * �P�j �N���}�X�^�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�y���������z<BR>
     * �@@�@@�@@�E�N���敪 �� ����.�N���敪 and<BR>
     * �@@�@@�@@�E�J�n�N����(�a��)(*1) �� ����.�a����� and<BR>
     * �@@�@@�@@�E����.�a����� �� �I���N����(�a��)(*2)<BR>
     * �@@�@@�@@�@@(*1) [�J�n�N(�a��)]��[�J�n����]����������������<BR>
     * �@@�@@�@@�@@(*2) [�I���N(�a��)]��[�I������]����������������<BR>
     * <BR>
     * �@@�@@�����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j ����N�����Z�o����B<BR>
     * �@@�@@ ����.�a�����̔N��(YY) �{ �i�擾�������R�[�h.�J�n�N(����) �| �擾�������R�[�h.�J�n�N(�a��)�j<BR>
     * <BR>
     * �R�j �Z�o��������N���ƈ���.�a�����̓��t����(MMDD)���������ADate�^�ŕԋp����B<BR>
     * @@param l_strJapaneseEra - (�N���敪)<BR>
     * �N���敪<BR>
     * @@param l_strDateString - (�a�����)<BR>
     * �a��iYYMMDD�`���j������<BR>
     * @@return Date
     */
    public static Date toDate(String l_strJapaneseEra, String l_strDateString)
    {
        final String STR_METHOD_NAME = "toDate(String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strJapaneseEra == null || l_strDateString == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if (!WEB3StringTypeUtility.isDigit(l_strDateString) || l_strDateString.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�j �N���}�X�^�[�e�[�u�����ȉ��̏����Ō�������B
        //�y���������z
        //�E�N���敪 �� ����.�N���敪 and
        //�J�n�N����(�a��)(*1) �� ����.�a����� and
        //����.�a����� �� �I���N����(�a��)(*2)
        String l_strWhere = "japanese_era_div = ?";
        l_strWhere = l_strWhere + " and concat(start_year_jap, start_date) <= ?";
        l_strWhere = l_strWhere + " and concat(end_year_jap, end_date) >= ?";

        Object[] l_objValues = {new Integer(l_strJapaneseEra), l_strDateString, l_strDateString};

        List l_lisEraRecords = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisEraRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);
        //����N�����Z�o����B
        //����.�a�����̔N��(YY) �{ �i�擾�������R�[�h.�J�n�N(����) �| �擾�������R�[�h.�J�n�N(�a��)�j
        int l_intDateString = Integer.parseInt(l_strDateString.substring(0, 2));
        int l_intStartYear = Integer.parseInt(l_eraRow.getStartYear());
        int l_intStartYearJap = Integer.parseInt(l_eraRow.getStartYearJap());

        String l_strYear = String.valueOf(l_intDateString + (l_intStartYear - l_intStartYearJap));

        //�R�j �Z�o��������N���ƈ���.�a�����̓��t����(MMDD)���������ADate�^�ŕԋp����B
        String l_strToDate = l_strYear + l_strDateString.substring(2, 6);
        Date l_datToDate =
            WEB3DateUtility.getDate(l_strToDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        log.exiting(STR_METHOD_NAME);
        return l_datToDate;
    }

    /**
     * �����̐�����t��N���A�a�����̔z��ɕϊ�����B<BR>
     * �P�j �N���}�X�^�[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�y���������z<BR>
     * �@@�@@�@@�E�J�n�N����(����)(*1) �� ����.���t and<BR>
     * �@@�@@�@@�E����.���t �� �I���N����(����)(*2)<BR>
     * �@@�@@�@@�@@(*1) [�J�n�N(����)]��[�J�n����]�������������������t�^�ɕϊ�<BR>
     * �@@�@@�@@�@@(*2) [�I���N(����)]��[�I������]�������������������t�^�ɕϊ�<BR>
     * <BR>
     * �@@�@@ �����R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j �a��N�����Z�o����B<BR>
     * �@@�@@ ����.������t�̔N��(YYYY) �| �i�擾�������R�[�h.�J�n�N(����) �| �擾�������R�[�h.�J�n�N(�a��)�j<BR>
     * �@@�@@ ���v�Z���ʂ��ꌅ�̏ꍇ�͂P���ڂ�"0"��ǉ��i��F1995-1990=5 �� 05�j<BR>
     * <BR>
     * �R�j ArrayList�𐶐����A�ȉ���ǉ�����B<BR>
     * �@@�@@ �E�擾�������R�[�h�̔N���敪<BR>
     * �@@�@@ �E�Z�o�����a��N��(YY)�ƈ���.������t�̌���(MMDD)����������������<BR>
     * <BR>
     * �S�j �쐬����ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * <BR>
     * �Ⴆ��<BR>
     * ���́F 1990.01.07<BR>
     * �o�́F {1, 010107}<BR>
     * @@param l_datInput - (������t)<BR>
     * ������t�B<BR>
     * @@return String[]
     */
    public static String[] toJapDate(Date l_datInput)
    {
        final String STR_METHOD_NAME = "toJapDate(Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datInput == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�j �N���}�X�^�[�e�[�u�����ȉ��̏����Ō�������B
        //�y���������z
        //�J�n�N����(����)(*1) �� ����.���t and����.���t �� �I���N����(����)(*2)
        String l_strWhere = "concat(start_year, start_date) <= ? and concat(end_year, end_date) >= ?";

        String l_strDatInput =
            WEB3DateUtility.formatDate(l_datInput, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Object[] l_objValues = {l_strDatInput, l_strDatInput};

        List l_lisEraRecords = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_objValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���R�[�h���擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisEraRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);
        //�Q�j �a��N�����Z�o����B
        //����.������t�̔N��(YYYY) �| �i�擾�������R�[�h.�J�n�N(����) �| �擾�������R�[�h.�J�n�N(�a��)�j
        int l_intInput = Integer.parseInt(l_strDatInput.substring(0, 4));
        int l_intStartYear = Integer.parseInt(l_eraRow.getStartYear());
        int l_intStartYearJap = Integer.parseInt(l_eraRow.getStartYearJap());

        String l_strYearJap = String.valueOf(l_intInput - (l_intStartYear - l_intStartYearJap));

        //�v�Z���ʂ��ꌅ�̏ꍇ�͂P���ڂ�"0"��ǉ��i��F1995-1990=5 �� 05�j
        if (l_strYearJap.length() == 1)
        {
            l_strYearJap = "0" + l_strYearJap;
        }

        //�R�j ArrayList�𐶐����A�ȉ���ǉ�����B
        //�擾�������R�[�h�̔N���敪
        //�Z�o�����a��N��(YY)�ƈ���.������t�̌���(MMDD)����������������
        List l_lisYears = new ArrayList();
        l_lisYears.add(l_eraRow.getJapaneseEraDiv() + "");
        l_lisYears.add(l_strYearJap + l_strDatInput.substring(4, 8));

        //�S�j �쐬����ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strJapDates = new String[l_lisYears.size()];
        l_lisYears.toArray(l_strJapDates);

        log.exiting(STR_METHOD_NAME);
        return l_strJapDates;
    }

    /**
     * �����̘a�����ɑΉ�����N���敪��ԋp����B<BR>
     * <BR>
     * �P�j �N���}�X�^�[�e�[�u�����擾����B<BR>
     * <BR>
     * �@@�@@�y���������z<BR>
     * �@@�@@�@@�E�N���敪 != 9�F�s��<BR>
     * <BR>
     * �@@�@@�y�\�[�g�����z<BR>
     * �@@�@@�@@�N���敪(�~��)<BR>
     * <BR>
     * �Q�j �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�y���������z<BR>
     * �@@�@@�@@���́i���ϐ����j = "era_condition_year"<BR>
     * <BR>
     * �R�j �Y������N���敪��ԋp����B<BR>
     * <BR>
     * �@@�R�|�P�j �u����.�a�����̔N��(YY) �� �擾�����V�X�e���v���t�@@�����X.�l�v�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@ �擾�����N���}�X�^�[�e�[�u���̂Q���ڂ̃��R�[�h.�N���敪��ԋp�B<BR>
     * <BR>
     * �@@�R�|�Q�j �V�X�e���v���t�@@�����X�ɊY�����R�[�h�����݂��Ȃ������ꍇ�A�܂��́A��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@ �擾�����N���}�X�^�[�e�[�u���̂P���ڂ̃��R�[�h.�N���敪��ԋp�B<BR>
     * <BR>
     * @@param l_strDateString - (�a��iYYMMDD�`���j������)<BR>
     * �a��iYYMMDD�`���j������<BR>
     * @@return String
     */
    public static String getJapEraDiv(String l_strDateString)
    {
        final String STR_METHOD_NAME = "getJapEraDiv(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strDateString == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        if (!WEB3StringTypeUtility.isDigit(l_strDateString) || l_strDateString.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�P�j �N���}�X�^�[�e�[�u�����擾����B
        //�y���������z
        //�E�N���敪 != 9�F�s��
        String l_strWhere = " japanese_era_div <> ? ";
        Object[] l_objValues = {new Integer(WEB3EraBornDef.UNKNOWN)};

        //�y�\�[�g�����z
        //�N���敪(�~��)
        String l_strSortCond = " japanese_era_div desc ";

        List l_lisEraRecords = new ArrayList();
        SystemPreferencesRow l_systemPreferencesRow = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisEraRecords = l_queryProcessor.doFindAllQuery(
                EraRow.TYPE,
                l_strWhere,
                l_strSortCond,
                null,
                l_objValues);

            if (l_lisEraRecords.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
            //�y���������z
            //���́i���ϐ����j = "era_condition_year"
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByName(WEB3SystemPreferencesNameDef.ERA_CONDITION_YEAR);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3StringTypeUtility.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�u����.�a�����̔N��(YY) �� �擾�����V�X�e���v���t�@@�����X.�l�v�̏ꍇ
        if (l_systemPreferencesRow != null
            && Integer.parseInt(l_strDateString.substring(0, 2)) >
                Integer.parseInt(l_systemPreferencesRow.getValue()))
        {
            //�擾�����N���}�X�^�[�e�[�u���̂Q���ڂ̃��R�[�h.�N���敪��ԋp�B
            if (l_lisEraRecords.size() > 1)
            {
                EraRow l_eraRow = (EraRow)l_lisEraRecords.get(1);

                log.exiting(STR_METHOD_NAME);
                return l_eraRow.getJapaneseEraDiv() + "";
            }
        }
        else
        {
            //�V�X�e���v���t�@@�����X�ɊY�����R�[�h�����݂��Ȃ������ꍇ�A�܂��́A��L�ȊO�̏ꍇ�A
            //�擾�����N���}�X�^�[�e�[�u���̂P���ڂ̃��R�[�h.�N���敪��ԋp�B
            if (l_lisEraRecords.size() > 0)
            {
                EraRow l_eraRow = (EraRow)l_lisEraRecords.get(0);

                log.exiting(STR_METHOD_NAME);
                return l_eraRow.getJapaneseEraDiv() + "";
            }
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}@

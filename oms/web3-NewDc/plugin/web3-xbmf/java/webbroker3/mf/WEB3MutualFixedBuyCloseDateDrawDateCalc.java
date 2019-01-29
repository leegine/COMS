head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCloseDateDrawDateCalc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �莞��z���t���ؓ��������v�Z(WEB3MutualFixedBuyCloseDateDrawDateCalc.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/09 ���g (���u) �V�K�쐬 ���f��No.607,612
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�莞��z���t���ؓ��������v�Z)<BR>
 * �莞��z���t���ؓ��������v�Z<BR>
 *
 * @@author ���g(���u)
 * @@version 1.0
 */
public class WEB3MutualFixedBuyCloseDateDrawDateCalc
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCloseDateDrawDateCalc.class);

    /**
     * (�ʏ������)<BR>
     * �ʏ������<BR>
     */
    private long usuallyDrawDate;

    /**
     * (�ܗ^������)<BR>
     * �ܗ^������<BR>
     */
    private long prizeAndDrawDate;

    /**
     * (�ʏ���ؓ��N�Z����)<BR>
     * �ʏ���ؓ��N�Z����<BR>
     */
    private long usuallyCloseDateBaseDate;

    /**
     * (�ܗ^���ؓ��N�Z����)<BR>
     * �ܗ^���ؓ��N�Z����<BR>
     */
    private long prizeAndCloseDateBaseDate;

    /**
     * (�莞��z���t���؎���)<BR>
     * �莞��z���t���؎���<BR>
     */
    private long fixedBuyCloseDate;

    /**
     * (calc�ʏ���ؓ��iWEB�j)<BR>
     * �w��N���̒ʏ���ؓ��iWEB�j���擾����B<BR>
     * <BR>
     * 1) this.calc�ʏ������()���R�[��<BR>
     * �@@�@@[calc�ʏ�������̈���]<BR>
     * �@@�@@�w��N���F����.�w��N��<BR>
     * <BR>
     * 2) �c�Ɠ��v�Z�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@[�c�Ɠ��v�Z�C���X�^���X�̈���]<BR>
     * �@@�@@�@@�@@����Fcalc�ʏ������()�̖߂�l<BR>
     * <BR>
     * 3) �擾�����c�Ɠ��v�Z�I�u�W�F�N�g.roll���R�[��<BR>
     * �@@�@@[roll�̈���]<BR>
     * �@@�@@���Z�E���Z�����F-(this.�ʏ���ؓ��N�Z����)<BR>
     * <BR>
     * 4) roll�̖߂�l�����^�[������B<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyCloseDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyCloseDate(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc�ʏ������()���R�[��
        //[calc�ʏ�������̈���]
        //�w��N���F����.�w��N��
        Date l_datUsuallyDrawDate = this.calcUsuallyDrawDate(l_datSelectMY);

        //2) �c�Ɠ��v�Z�C���X�^���X�𐶐�����B
        //[�c�Ɠ��v�Z�C���X�^���X�̈���]
        //����Fcalc�ʏ������()�̖߂�l
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datUsuallyDrawDate.getTime()));

        //3) �擾�����c�Ɠ��v�Z�I�u�W�F�N�g.roll���R�[��
        //[roll�̈���]
        //���Z�E���Z�����F-(this.�ʏ���ؓ��N�Z����)
        Timestamp l_tsBizDate =
            l_gentradeBizDate.roll(-(int)this.usuallyCloseDateBaseDate);

        //4) roll�̖߂�l�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_tsBizDate);
    }

    /**
     * (calc�ʏ������)<BR>
     * �w��N���̒ʏ���������擾����B<BR>
     * ���w��N���̒ʏ����������c�Ɠ��̏ꍇ�́A���c�Ɠ����擾����B<BR>
     * <BR>
     * 1)�@@����.�w��N���̔N��(yyyy/mm)��this.�ʏ��������A���������A<BR>
     * �@@�@@Date�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * 2)�@@������ԊǗ��C���X�^���X���쐬�B<BR>
     * <BR>
     * 3)�@@�擾����������ԊǗ�.get�c�Ɠ��敪()���R�[���B<BR>
     * �@@�@@[get�c�Ɠ��敪�̈���]<BR>
     * �@@�@@�@@���t�F�쐬����Date�I�u�W�F�N�g<BR>
     * <BR>
     * 4)�@@�擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     * �@@�@@4)-1)�@@�쐬����Date�I�u�W�F�N�g�����^�[������B<BR>
     * <BR>
     * 5)�@@�擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     * �@@�@@5)-1) �쐬����Date�I�u�W�F�N�g���C���N�������g���A<BR>
     * �@@�@@�@@�擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�ɂȂ�܂ŌJ��Ԃ��B<BR>
     * �@@�@@�@@�@@�@@�@@�h�c�Ɠ��h�ɂȂ�����A�쐬����Date�I�u�W�F�N�g�����^�[������B<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyDrawDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyDrawDate(Date)";
        log.entering(STR_METHOD_NAME);

        String l_strUsuallyDrawDate = String.valueOf(this.usuallyDrawDate);
        Date l_datSelectDMY;
        String l_strSelectMYDate = WEB3DateUtility.formatDate(
            l_datSelectMY, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        //1)  ����.�w��N���̔N��(yyyy/mm)��this.�ʏ��������A���������ADate�I�u�W�F�N�g���쐬����B
        if (l_strUsuallyDrawDate.length() == 1)
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + "0" + l_strUsuallyDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + l_strUsuallyDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }

        //2) ������ԊǗ��C���X�^���X���쐬�B
        //3) �擾����������ԊǗ�.get�c�Ɠ��敪()���R�[���B
        //[get�c�Ɠ��敪�̈���]
        //���t�F�쐬����Date�I�u�W�F�N�g
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_datSelectDMY.getTime()));

        //4) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�̏ꍇ
        if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateType))
        {
            //4)-1) �쐬����Date�I�u�W�F�N�g�����^�[������B
            log.exiting(STR_METHOD_NAME);
            return l_datSelectDMY;
        }

        Timestamp l_tsBizDate = new Timestamp(l_datSelectDMY.getTime());
        //5) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            //5)-1) �쐬����Date�I�u�W�F�N�g���C���N�������g���A�擾����������ԊǗ�.get�c�Ɠ��敪()
            //�̖߂�l���h�c�Ɠ��h�ɂȂ�܂ŌJ��Ԃ��B
            //�h�c�Ɠ��h�ɂȂ�����A�쐬����Date�I�u�W�F�N�g�����^�[������B
            l_tsBizDate = new WEB3GentradeBizDate(l_tsBizDate).roll(1);
        }

        log.exiting(STR_METHOD_NAME);
        return new Date(l_tsBizDate.getTime());
    }

    /**
     * (calc�ܗ^���ؓ�)<BR>
     * �w��N���̏ܗ^���ؓ����擾����B<BR>
     * �w��N���̏ܗ^����������ܗ^���ؓ��N�Z�����O�̓��t���擾����<BR>
     * <BR>
     * 1) this.calc�ܗ^������()���R�[��<BR>
     * �@@�@@[calc�ܗ^�������̈���]<BR>
     * �@@�@@�w��N���F����.�w��N��<BR>
     * <BR>
     * 2) �c�Ɠ��v�Z�C���X�^���X�𐶐�����B<BR>
     * �@@�@@�@@[�c�Ɠ��v�Z�C���X�^���X�̈���]<BR>
     * �@@�@@�@@�@@����Fcalc�ܗ^������()�̖߂�l<BR>
     * <BR>
     * 3) �擾�����c�Ɠ��v�Z�I�u�W�F�N�g.roll���R�[��<BR>
     * �@@�@@[roll�̈���]<BR>
     * �@@�@@���Z�E���Z�����F-(this.�ܗ^���ؓ��N�Z����)<BR>
     * <BR>
     * 4) roll�̖߂�l�����^�[������B<BR>
     * <BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndCloseDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndCloseDate(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc�ܗ^������()���R�[��
        //[calc�ܗ^�������̈���]
        //�w��N���F����.�w��N��
        Date l_datPrizeAndDrawDate = this.calcPrizeAndDrawDate(l_datSelectMY);

        //2) �c�Ɠ��v�Z�C���X�^���X�𐶐�����B
        //[�c�Ɠ��v�Z�C���X�^���X�̈���]
        //����Fcalc�ܗ^������()�̖߂�l
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(
                new Timestamp(l_datPrizeAndDrawDate.getTime()));

        //3) �擾�����c�Ɠ��v�Z�I�u�W�F�N�g.roll���R�[��
        //[roll�̈���]
        //���Z�E���Z�����F-(this.�ܗ^���ؓ��N�Z����)
        Timestamp l_tsBizDate =
            l_gentradeBizDate.roll(-(int)this.prizeAndCloseDateBaseDate);

        //4) roll�̖߂�l�����^�[������B
        log.exiting(STR_METHOD_NAME);
        return l_tsBizDate;
    }

    /**
     * (calc�ܗ^������)<BR>
     * �w��N���̏ܗ^���������擾����B<BR>
     * ���w��N���̏ܗ^����������c�Ɠ��̏ꍇ�́A���c�Ɠ����擾����B<BR>
     * <BR>
     * 1) ����.�w��N���̔N��(yyyy/mm)��this.�ܗ^��������A���������A<BR>
     * �@@Date�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * 2) ������ԊǗ��C���X�^���X���쐬�B<BR>
     * <BR>
     * 3) �擾����������ԊǗ�.get�c�Ɠ��敪()���R�[���B<BR>
     * �@@�@@[get�c�Ɠ��敪�̈���]<BR>
     * �@@�@@�@@���t�F�쐬����Date�I�u�W�F�N�g<BR>
     * <BR>
     * 4) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     * �@@�@@4)-1) �쐬����Date�I�u�W�F�N�g�����^�[������B<BR>
     * <BR>
     * 5) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ<BR>
     * <BR>
     * �@@�@@5)-1) �쐬����Date�I�u�W�F�N�g���C���N�������g���A<BR>
     * �@@�@@�@@�擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�ɂȂ�܂ŌJ��Ԃ��B<BR>
     * �@@�@@�@@�h�c�Ɠ��h�ɂȂ�����A�쐬����Date�I�u�W�F�N�g�����^�[������B<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndDrawDate(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndDrawDate(Date)";
        log.entering(STR_METHOD_NAME);

        String l_strPrizeAndDrawDate = String.valueOf(this.prizeAndDrawDate);
        Date l_datSelectDMY;
        String l_strSelectMYDate = WEB3DateUtility.formatDate(
            l_datSelectMY, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        //1)  ����.�w��N���̔N��(yyyy/mm)��this.�ܗ^��������A���������ADate�I�u�W�F�N�g���쐬����B
        if (l_strPrizeAndDrawDate.length() == 1)
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + "0" + l_strPrizeAndDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_datSelectDMY = WEB3DateUtility.getDate(
                l_strSelectMYDate + l_strPrizeAndDrawDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }

        //2) ������ԊǗ��C���X�^���X���쐬�B
        //3) �擾����������ԊǗ�.get�c�Ɠ��敪()���R�[���B
        //[get�c�Ɠ��敪�̈���]
        //���t�F�쐬����Date�I�u�W�F�N�g
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(l_datSelectDMY.getTime()));

        //4) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h�c�Ɠ��h�̏ꍇ
        if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateType))
        {
            //4)-1) �쐬����Date�I�u�W�F�N�g�����^�[������B
            log.exiting(STR_METHOD_NAME);
            return l_datSelectDMY;
        }

        Timestamp l_tsBizDate = new Timestamp(l_datSelectDMY.getTime());
        //5) �擾����������ԊǗ�.get�c�Ɠ��敪()�̖߂�l���h��c�Ɠ��h�̏ꍇ
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            //5)-1) �쐬����Date�I�u�W�F�N�g���C���N�������g���A�擾����������ԊǗ�.get�c�Ɠ��敪()
            //�̖߂�l���h�c�Ɠ��h�ɂȂ�܂ŌJ��Ԃ��B
            //�h�c�Ɠ��h�ɂȂ�����A�쐬����Date�I�u�W�F�N�g�����^�[������B
            l_tsBizDate = new WEB3GentradeBizDate(l_tsBizDate).roll(1);
        }

        log.exiting(STR_METHOD_NAME);
        return new Date(l_tsBizDate.getTime());
    }

    /**
     * (calc�ʏ���ؓ����iWEB�j)<BR>
     * �w��N���̒ʏ���ؓ����iWEB�j���擾����B<BR>
     * �w��N���̒ʏ���ؓ��iWEB�j�̒莞��z���t���؎��Ԃ��擾����<BR>
     * <BR>
     * 1) this.calc�ʏ���ؓ��iWEB�j()���R�[��<BR>
     * �@@�@@[calc�ʏ���ؓ��iWEB�j�̈���]<BR>
     * �@@�@@�w��N���F����.�w��N��<BR>
     * <BR>
     * 2) calc�ʏ���ؓ��iWEB�j�̖߂�l��this.�莞��z���t���؎��Ԃ�A�������l�����^�[������B<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcUsuallyCloseDateTime(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcUsuallyCloseDateTime(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc�ʏ���ؓ��iWEB�j()���R�[��
        //[calc�ʏ���ؓ��iWEB�j�̈���]
        //�w��N���F����.�w��N��
        Date l_datUsuallyCloseDate = this.calcUsuallyCloseDate(l_datSelectMY);

        String l_strFixedBuyCloseDate = String.valueOf(this.fixedBuyCloseDate);
        Date l_datReturnDate;
        String l_strUsuallyCloseDate = WEB3DateUtility.formatDate(
            l_datUsuallyCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //2) calc�ʏ���ؓ��iWEB�j�̖߂�l��this.�莞��z���t���؎��Ԃ�A�������l�����^�[������B
        if (l_strFixedBuyCloseDate.length() == 5)
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                l_strUsuallyCloseDate + "0" + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                l_strUsuallyCloseDate + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturnDate;
    }

    /**
     * (calc�ܗ^���ؓ���)<BR>
     * �w��N���̏ܗ^���ؓ������擾����B<BR>
     * �w��N���̏ܗ^���ؓ��̒莞��z���t���؎��Ԃ��擾����<BR>
     * <BR>
     * 1) this.calc�ܗ^���ؓ�()���R�[��<BR>
     * �@@�@@[calc�ܗ^���ؓ��̈���]<BR>
     * �@@�@@�w��N���F����.�w��N��<BR>
     * <BR>
     * 2) calc�ܗ^���ؓ��̖߂�l��this.�莞��z���t���؎��Ԃ�A�������l�����^�[������B<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcPrizeAndCloseDateHour(Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcPrizeAndCloseDateHour(Date)";
        log.entering(STR_METHOD_NAME);

        //1) this.calc�ܗ^���ؓ�()���R�[��
        //[calc�ܗ^���ؓ��̈���]
        //�w��N���F����.�w��N��
        Date l_datPrizeAndCloseDate = this.calcPrizeAndCloseDate(l_datSelectMY);

        String l_strFixedBuyCloseDate = String.valueOf(this.fixedBuyCloseDate);
        Date l_datReturnDate;
        String l_strPrizeAndCloseDate = WEB3DateUtility.formatDate(
                l_datPrizeAndCloseDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //2) calc�ܗ^���ؓ��̖߂�l��this.�莞��z���t���؎��Ԃ�A�������l�����^�[������B
        if (l_strFixedBuyCloseDate.length() == 5)
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                    l_strPrizeAndCloseDate + "0" + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            l_datReturnDate = WEB3DateUtility.getDate(
                    l_strPrizeAndCloseDate + l_strFixedBuyCloseDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturnDate;
    }

    /**
     * (�莞��z���t���ؓ��������v�Z)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyCloseDateDrawDateCalc()
    {

    }

    /**
     * (�莞��z���t���ؓ��������v�Z)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * 1) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ʏ�������փZ�b�g����B<BR>
     * [����]<BR>
     * �@@���́i���ϐ����j�FXX_SBS_DRAW_DD<BR>
     * �@@��XX�͈���.�،���ЃR�[�h<BR>
     * <BR>
     * 2) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ʏ���ؓ��N�Z�����փZ�b�g����B<BR>
     * [����]<BR>
     * �@@���́i���ϐ����j�FXX_SBS_DAY_COUNT<BR>
     * �@@��XX�͈���.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ܗ^�������փZ�b�g����B<BR>
     * [����]<BR>
     * �@@���́i���ϐ����j�FXX_SBS_DRAW_DD_BONUS<BR>
     * �@@��XX�͈���.�،���ЃR�[�h<BR>
     * <BR>
     * 4) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this�ܗ^���ؓ��N�Z�����փZ�b�g����B<BR>
     * [����]<BR>
     * �@@���́i���ϐ����j�FXX_SBS_DAY_COUNT_BONUS<BR>
     * �@@��XX�͈���.�،���ЃR�[�h<BR>
     * <BR>
     * 5) ������ԃe�[�u�����������A������ԃe�[�u��Row.�J�n���Ԃ�this.�莞��z���t���؎��ԂփZ�b�g����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F����.���X�R�[�h<BR>
     * �@@�@@�s��R�[�h�F0�FDEFAULT<BR>
     * �@@�@@��t���ԋ敪�F33�F���M�莞��z���t<BR>
     * �@@�@@���i�R�[�h:0�FDEFAULT<BR>
     * �@@�@@�c�Ɠ��敪�F1�F�I���c�Ɠ�<BR>
     * �@@�@@��t�\�F0�F��t�\<BR>
     * �@@�@@�������v�Z�F1�F���c�Ɠ�<BR>
     * �@@�@@�� �擾�������R�[�h��1���ȊO�Ȃ�u�f�[�^�s�����G���[�v�̗�O���X���[����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyCloseDateDrawDateCalc(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3MutualFixedBuyCloseDateDrawDateCalc("
            + "String, String)";
        log.entering(STR_METHOD_NAME);

        SystemPreferencesRow l_systemPreferencesRow = null;
        //1) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ʏ�������փZ�b�g����B
        //[����]
        //���́i���ϐ����j�FXX_SBS_DRAW_DD
        //��XX�͈���.�،���ЃR�[�h
        SystemPreferencesPK l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.usuallyDrawDate = Long.parseLong(
                l_systemPreferencesRow.getValue());
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

        //2) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ʏ���ؓ��N�Z�����փZ�b�g����B
        //[����]
        //���́i���ϐ����j�FXX_SBS_DAY_COUNT
        //��XX�͈���.�،���ЃR�[�h
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.usuallyCloseDateBaseDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
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

        //3) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this.�ܗ^�������փZ�b�g����B
        //[����]
        //���́i���ϐ����j�FXX_SBS_DRAW_DD_BONUS
        //��XX�͈���.�،���ЃR�[�h
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.prizeAndDrawDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
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

        //4) �V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����ɊY�����郌�R�[�h���擾���A�l��this�ܗ^���ؓ��N�Z�����փZ�b�g����B
        //[����]
        //���́i���ϐ����j�FXX_SBS_DAY_COUNT_BONUS
        //��XX�͈���.�،���ЃR�[�h
        l_systemPreferencesPK =
            new SystemPreferencesPK(l_strInstitutionCode
                + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
        try
        {
            l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);
            this.prizeAndCloseDateBaseDate =
                Long.parseLong(l_systemPreferencesRow.getValue());
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

        //5) ������ԃe�[�u�����������A������ԃe�[�u��Row.�J�n���Ԃ�this.�莞��z���t���؎��ԂփZ�b�g����B
        //[����]
        //�،���ЃR�[�h�F����.�،���ЃR�[�h
        //���X�R�[�h�F����.���X�R�[�h
        //�s��R�[�h�F0�FDEFAULT
        //��t���ԋ敪�F33�F���M�莞��z���t
        //���i�R�[�h:0�FDEFAULT
        //�c�Ɠ��敪�F1�F�I���c�Ɠ�
        //��t�\�F0�F��t�\
        //�������v�Z�F1�F���c�Ɠ�
        //�� �擾�������R�[�h��1���ȊO�Ȃ�u�f�[�^�s�����G���[�v�̗�O���X���[����B
        String l_strWhereClause = " institution_code = ? and branch_code = ?"
            + " and market_code = ? and trading_time_type = ? and product_code = ?"
            + " and biz_date_type = ? and enable_order = ? and bizdate_calc_parameter =? ";

        Object[] l_bindVars =
        {
            l_strInstitutionCode,
            l_strBranchCode,
            WEB3MarketCodeDef.DEFAULT,
            WEB3TradingTimeTypeDef.MF_FIXED_BUYING,
            WEB3ProductCodeDef.DEFAULT,
            WEB3BizDateTypeDef.BIZ_DATE,
            WEB3EnableOrderDef.ENABLE,
            WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE
        };
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisReturnLists =
                l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
            int l_intSize = l_lisReturnLists.size();
            if (l_intSize == 0 || l_intSize > 1)
            {
                log.debug("�f�[�^�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�f�[�^�s�����G���[�B");
            }
            else
            {
                TradingTimeRow l_tradingTimeRow =
                    (TradingTimeRow)l_lisReturnLists.get(0);
                this.fixedBuyCloseDate =
                    Long.parseLong(l_tradingTimeRow.getStartTime());
            }
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
}

@

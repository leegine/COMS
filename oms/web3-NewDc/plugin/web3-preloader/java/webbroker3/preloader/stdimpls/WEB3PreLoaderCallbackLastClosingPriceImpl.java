head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.21.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackLastClosingPriceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3PreLoaderCallbackLastClosingPriceImpl�N���X(WEB3PreLoaderCallbackLastClosingPriceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.preloader.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.gentrade.data.LastClosingPriceDao;
import webbroker3.preloader.WEB3PreLoaderCallback;
import webbroker3.util.WEB3LogUtility;


/**
 * LAST_CLOSING_PRICE�e�[�u���p��PreLoaderCallback�N���X
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackLastClosingPriceImpl implements WEB3PreLoaderCallback
{
    
    private static final WEB3LogUtility LOG = 
        WEB3LogUtility.getInstance(WEB3PreLoaderCallbackLastClosingPriceImpl.class);
    
    private static final boolean DBG = LOG.ison();
    
    private Hashtable baseDateHolder;
    
    public WEB3PreLoaderCallbackLastClosingPriceImpl()
    {
        baseDateHolder = new Hashtable();
    }

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        ProductRow l_pRow = (ProductRow) l_row;
        Timestamp l_baseDate = getBaseDate();
        try {
            LastClosingPriceDao.findRowByPk(l_pRow.getProductId(), l_baseDate);
        } catch (DataFindException l_dfe)
        {
            // ���R�[�h�����݂��Ȃ��ꍇ�͉������Ȃ��B
        }
        
    }
    
    /**
     * ������擾����B
     * 
     * �p�t�H�[�}���X�������邽�߁ATradingSystem#getBizDate()�̖߂�l����A
     * �u�����b�~���b�v���N���A���Ă��琶������Timestamp���L���b�V�����Ă����B
     * 
     * @@return ���
     */
    private Timestamp getBaseDate()
    {
        Date l_bizDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_baseDate = (Timestamp) baseDateHolder.get(l_bizDate);
        if (l_baseDate == null)
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.setTime(l_bizDate);
            l_calendar.clear(Calendar.HOUR);
            l_calendar.clear(Calendar.MINUTE);
            l_calendar.clear(Calendar.SECOND);
            l_calendar.clear(Calendar.MILLISECOND);
            l_baseDate = new Timestamp(l_calendar.getTimeInMillis());
            baseDateHolder.put(l_bizDate, l_baseDate);
            if (DBG)
            {
                LOG.info("new base date entry :" + l_baseDate);
            }
        }
        return l_baseDate;
        
    }

}
@

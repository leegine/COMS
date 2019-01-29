head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3DefaultPreLoader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3DefaultPreLoader�N���X(WEB3DefaultPreLoader.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 �R�c�@@��i (FLJ) �V�K�쐬
                  : 2005/04/19 �R�c�@@��i (FLJ) �v�����[�h�����[�h�I�����[���[�h�Ŏ��s����悤�ɕύX
 */
package webbroker3.preloader;

import java.util.Iterator;
import java.util.List;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

/**
 * WEB3PreLoader�C���^�[�t�F�[�X�̃f�t�H���g�����N���X�B<BR>
 * <BR>
 * �ʏ�A�L���b�V���E�v�����[�h���s���e�[�u�����Ƃɂ��̃N���X�̃C���X�^���X���쐬���A
 * �L���b�V���ւ̃��[�f�B���O���s���B
 * <BR>
 * �����[�f�B���O�̎菇��<BR>
 * 1.�R���X�g���N�^�Őݒ肳�ꂽRowType�̃e�[�u���̃f�[�^��
 * �@@QueryProcessor#doFindAllQuery(RowType, String, String String, Object[])
 * �@@���\�b�h�Ō�������B<BR>
 * �@@���ݒ肷��p�����[�^��<BR>
 * �@@RowType = �R���X�g���N�^�Ŏw�肵���p�����[�^�I�u�W�F�N�g#getRowType()<BR>
 *     �i���w��̏ꍇ�́A�R���X�g���N�^�Őݒ肵��RowType�j<BR> 
 * �@@String�iWhere��j = �R���X�g���N�^�Ŏw�肵���p�����[�^�I�u�W�F�N�g#getWhere()
 * �@@�@@�i���w��̏ꍇ��<code>null</code>�j<BR>
 * �@@String�iOrderBy��j = �R���X�g���N�^�Ŏw�肵���p�����[�^�I�u�W�F�N�g#getOrderBy()
 * �@@�@@�i���w��̏ꍇ��<code>null</code>�j<BR>
 * �@@String�iConditions�j = �R���X�g���N�^�Ŏw�肵���p�����[�^�I�u�W�F�N�g#getConditions()
 * �@@�@@�i���w��̏ꍇ��<code>null</code>�j<BR>
 * �@@Object[]�i�o�C���h�ϐ��j = �R���X�g���N�^�Ŏw�肵���p�����[�^�I�u�W�F�N�g#getBindVars()
 * �@@�@@�i���w��̏ꍇ��<code>null</code>�j<BR>
 * 
 * 2.�u1.�v�Ō������ꂽ�e���R�[�h�ɑ΂��āA
 * �@@�R���X�g���N�^�Ŏw�肵��WEB3PreLoaderCallback#process(Row)���\�b�h��
 * �@@�Ăяo���B<BR>
 * �@@���ݒ肷��p�����[�^��<BR>
 * �@@Row = �u1.�v�Ō������ꂽ�e���R�[�h��Row�I�u�W�F�N�g<BR> 
 * �@@�@@�i���w��̏ꍇ��WEB3DefaultPreLoaderCallbck�̃C���X�^���X�j
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3DefaultPreLoader implements WEB3PreLoader
{

    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility LOG =
        WEB3LogUtility.getInstance(WEB3DefaultPreLoader.class);
    
    /**
     * �ŏ��Ƀ��[�h����e�[�u����RowType
     */
    private final RowType rowType;

    /**
     * �e���R�[�h���L���b�V���ɓo�^���邽�߂̏������L�q���ꂽCallback�N���X�̃C���X�^���X
     */
    private final WEB3PreLoaderCallback callback;

    /**
     * �ŏ��Ƀ��[�h����Ƃ��̏�����ێ�����p�����[�^�I�u�W�F�N�g
     */
    private final WEB3DefaultPreLoaderParams params;

    /**
     * �R���X�g���N�^
     * 
     * @@param l_rowType �ŏ��Ƀ��[�h����e�[�u����RowType
     */
    public WEB3DefaultPreLoader(RowType l_rowType)
    {
        this(
            l_rowType,
            new PreLoaderCallbackImpl(),
            WEB3DefaultPreLoaderParams.getDefaultParams());
    }

    /**
     * �R���X�g���N�^
     * 
     * @@param l_rowType �ŏ��Ƀ��[�h����e�[�u����RowType
     * @@param l_callback �e���R�[�h���L���b�V���ɓo�^���邽�߂̏������L�q���ꂽCallback�N���X�̃C���X�^���X
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback)
    {
        this(
            l_rowType,
            l_callback,
            WEB3DefaultPreLoaderParams.getDefaultParams());
    }

    /**
     * �R���X�g���N�^
     * 
     * @@param l_rowType �ŏ��Ƀ��[�h����e�[�u����RowType
     * @@param l_params �ŏ��Ƀ��[�h����Ƃ��̏�����ێ�����p�����[�^�I�u�W�F�N�g
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3DefaultPreLoaderParams l_params)
    {
        this(l_rowType, new PreLoaderCallbackImpl(), l_params);
    }

    /**
     * �R���X�g���N�^
     * 
     * @@param l_rowType �ŏ��Ƀ��[�h����e�[�u����RowType
     * @@param l_callback �e���R�[�h���L���b�V���ɓo�^���邽�߂̏������L�q���ꂽCallback�N���X�̃C���X�^���X
     * @@param l_params �ŏ��Ƀ��[�h����Ƃ��̏�����ێ�����p�����[�^�I�u�W�F�N�g
     */
    public WEB3DefaultPreLoader(
        RowType l_rowType,
        WEB3PreLoaderCallback l_callback,
        WEB3DefaultPreLoaderParams l_params)
    {
        rowType = l_rowType;
        callback = l_callback;
        params = l_params;
    }

    /**
     * �o�^����Ă���RowType��Ԃ��B
     * 
     * @@return RowType
     */
    public RowType getRowType()
    {
        return rowType;
    }

    /**
     * �o�^����Ă���Callback�I�u�W�F�N�g��Ԃ��B
     * 
     * @@return Callback�I�u�W�F�N�g
     */
    public WEB3PreLoaderCallback getCallback()
    {
        return callback;
    }

    /**
     * �o�^���ꂽ�p�����[�^�I�u�W�F�N�g��Ԃ��B
     * 
     * @@return �p�����[�^�I�u�W�F�N�g
     */
    public WEB3DefaultPreLoaderParams getParams()
    {
        return params;
    }

    /**
     * �L���b�V���ւ̃��[�f�B���O���s���B
     * 
     * @@see webbroker3.preloader.WEB3PreLoader#execute()
     */
    public void execute()
    {

        boolean l_hasAlreadyStarted = WEB3CacheStatisticsUtils.startCollecting(); 

        LOG.info("Start loading. [table name=" + rowType.getTableName()
                 + ", callback class=" + getCallback().getClass().getName()
                 + ", initial parameters=" + getParams().toString() + "]");
        
        try
        {
            
            long l_lngStart = System.currentTimeMillis();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            TransactionCallback l_callback = new PreLoaderTransactionCallback();
            l_qp.doTransaction(l_callback);
            
            long l_lngEnd = System.currentTimeMillis();
            
            String l_strTableName = rowType.getTableName();
            long l_lngRowCacheCnt = WEB3CacheStatisticsUtils.getCachedItemCount(l_strTableName, "row");
            long l_lngEnumCacheCnt = WEB3CacheStatisticsUtils.getCachedItemCount(l_strTableName, "enum");
            
            
            LOG.info("Loading completed. table_name=" + l_strTableName 
                     + ", row=" + l_lngRowCacheCnt 
                     + ", enum=" + l_lngEnumCacheCnt 
                     + ", process_time=" + (l_lngEnd - l_lngStart) + "[msec]");
            
        } catch (DataException l_de)
        {
            LOG.error("Unexpected Exception occured while loading.", l_de);
        } finally {
            
            WEB3CacheStatisticsUtils.stopCollecting(l_hasAlreadyStarted);

        }
    }
    
    /**
     * WEB3DefaultPreLoaderCallback�̃f�t�H���g�����N���X
     * 
     * @@author Takuji Yamada (FLJ)
     */
    private static class PreLoaderCallbackImpl
        extends WEB3DefaultPreLoaderCallback
    {

        /**
        * @@see webbroker3.preloader.WEB3DefaultPreLoaderCallback#load(com.fitechlabs.dbind.Row)
        */
        protected void load(Row l_row) throws DataException
        {
        }

    }

    /**
     * ���[�f�B���O�������s��TransactionCallback�N���X
     * 
     * @@author Takuji Yamada (FLJ)
     */
    private class PreLoaderTransactionCallback implements TransactionCallback
    {
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            RowType l_rowType = (params.getRowType() != null) ? 
                    params.getRowType() : getRowType();
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_rows =
                l_qp.doFindAllQuery(
                    l_rowType,
                    params.getWhere(),
                    params.getOrderBy(),
                    params.getConditions(),
                    params.getBindVars());
            if (l_rows != null)
            {
                try
                {
                    
                    l_qp.forceReadonlyMode(getRowType(), true);
                    
                    for (Iterator l_it = l_rows.iterator(); l_it.hasNext();)
                    {
                        Row l_row = (Row) l_it.next();
                        getCallback().process(l_row);
                    }

                } catch (DataException l_de)
                {
                    throw new DataCallbackException(l_de.getMessage(), l_de);
                } finally
                {
                    l_qp.forceReadonlyMode(getRowType(), false);
                }
            }
            return null;
        }
    }

}
@

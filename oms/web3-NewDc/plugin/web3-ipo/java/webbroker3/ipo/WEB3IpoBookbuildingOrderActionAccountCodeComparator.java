head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderActionAccountCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O�\������.�ڋq�R�[�hComparator(WEB3IpoBookbuildingOrderActionAccountCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo;

import java.util.Comparator;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �u�b�N�r���f�B���O�\������.�ڋq�R�[�hComparator
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderActionAccountCodeComparator implements Comparator 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoBookbuildingOrderActionAccountCodeComparator.class);        
    
    /**
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     */
    private String orderBy;
    
    /**
     * @@roseuid 411308340217
     */
    public WEB3IpoBookbuildingOrderActionAccountCodeComparator() 
    {
     
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������.�ڋq�R�[�hComparator)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����̒l��this.orderBy�ɃZ�b�g����B
     * @@param l_strOrderBy - (orderBy)<BR>
     * �����i�Fasc�j�A�~���i�Fdesc�j���w�肷��t���O�B<BR>
     * <BR>
     * �@@A�F���� <BR>
     * �@@D�F�~��
     * 
     * @@return webbroker3.ipo.WEB3IpoBookbuildingOrderActionAccountCodeComparator
     * @@roseuid 40EE9675012A
     */
    public WEB3IpoBookbuildingOrderActionAccountCodeComparator(String l_strOrderBy) 
    {
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            
            throw new IllegalArgumentException("�p�����[�^�̒l��'A�F����'�A'D�F�~��'�ȊO�ł�");
            
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * �icompare�̎����j<BR>
     * <BR>
     * �ڋq�R�[�h�̔�r���s���B <BR>
     * <BR>
     * �P�j�@@������cast<BR>
     * �@@�������ޯ�����ިݸސ\�������P�A�ޯ�����ިݸސ\�������Q��<BR>�ޯ�����ިݸސ\�������^��cast����B<BR>
     * <BR>
     * �Q�j�@@��r <BR>
     * �@@�P�j��cast�����ޯ�����ިݸސ\�������P�A�ޯ�����ިݸސ\�������Q�ɂ��āA<BR>
     * ���ꂼ����ޯ�����ިݸސ\������.get�����h�c()�ɊY������ڋq���擾����B<BR>
     * <BR>
     * �@@[�����w��̏ꍇ�ithis.orderBy == �h�����h�j]<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() < �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() = �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() > �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * <BR>
     * �@@[�~���w��̏ꍇ�ithis.orderBy == �h�~���h�j]<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() < �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A���̐����i1�j��ԋp����B<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() = �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A0��ԋp����B<BR>
     * �@@�E�i�ޯ�����ިݸސ\�������P.�ڋq.getAccountCode() > �ޯ�����ިݸ�<BR>
     * �\�������Q.�ڋq.getAccountCode()�j�̏ꍇ�A���̐����i-1�j��ԋp����B<BR>
     * @@param l_bookbuildingOrderAction1 - �ޯ�����ިݸސ\�������P
     * 
     * @@param l_bookbuildingOrderAction2 - �ޯ�����ިݸސ\�������Q
     * 
     * @@return int
     * @@roseuid 40EE9675011B
     */
    public int compare(Object l_bookbuildingOrderAction1, Object l_bookbuildingOrderAction2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@������cast
        if( ! (l_bookbuildingOrderAction1 instanceof WEB3IpoBookbuildingOrderActionImpl) 
            || !(l_bookbuildingOrderAction2 instanceof WEB3IpoBookbuildingOrderActionImpl))
        {
            String l_strErrorMessage = 
                "�p�����[�^�̗ތ^���s���A�Y������'WEB3IpoBookbuildingOrderActionImpl' �ތ^�B";
            log.error(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }
        
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction1 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction1;
        WEB3IpoBookbuildingOrderActionImpl l_bookBuildingIpoOrderAction2 = 
            (WEB3IpoBookbuildingOrderActionImpl )l_bookbuildingOrderAction2;

        //�Q�j�@@��r
        
        //Get Account id
        long l_lngAccountID1 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction1.getDataSourceObject())).getAccountId();
        long l_lngAccountID2 = ((IpoBookbuildingOrderActionRow)(l_bookBuildingIpoOrderAction2.getDataSourceObject())).getAccountId();
        
        try
        {
            //construct Account
            MainAccount l_Account1 = new WEB3GentradeMainAccount(l_lngAccountID1);//DataFindException, DataQueryException, DataNetworkException
            MainAccount l_Account2 = new WEB3GentradeMainAccount(l_lngAccountID2);//DataFindException, DataQueryException, DataNetworkException           

            //compare
            if(WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            else if(WEB3AscDescDef.DESC.equals(this.orderBy))
            { 
                if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if(l_Account1.getAccountCode().compareTo(l_Account2.getAccountCode()) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
            else
            {
                String l_strErrorMessage = 
                    "�����A�~�� undefined.";
                log.error(l_strErrorMessage);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage);
            }
        }
        catch (DataFindException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataQueryException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e);
        }
    }
    
    /**
     * �iequals�̎����j<BR>
     * <BR>
     * ���g�p�B<BR>
     * false��ԋp����B
     * @@param l_arg0 - (arg0)
     * @@return boolean
     * @@roseuid 40EE9675011E
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@

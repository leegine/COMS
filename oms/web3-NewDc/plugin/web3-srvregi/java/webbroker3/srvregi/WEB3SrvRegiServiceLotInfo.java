head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceLotInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���I���(WEB3SrvRegiServiceLotInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 �s�p (���u) �V�K�쐬
*/
package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.SrvRegiLotInfoParams;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���I���)<BR>
 * �T�[�r�X���I���N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3SrvRegiServiceLotInfo implements BusinessObject 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceLotInfo.class);
    
    /**
     * (�T�[�r�X���I���s)<BR>
     */
    private SrvRegiLotInfoParams srvLotInfoParams;
    
    /**
     * (�T�[�r�X���I���)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐����A<BR>
     * ����.�T�[�r�X���I���Row��this.�T�[�r�X���I���s�ɃZ�b�g����B<BR>
     * @@param l_srvLotInfoRow - (�T�[�r�X���I���Row)<BR>
     * @@roseuid 412F0ADD0334
     */
    protected WEB3SrvRegiServiceLotInfo(SrvRegiLotInfoRow l_srvLotInfoRow) 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiServiceLotInfo(SrvRegiLotInfoRow) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams = new SrvRegiLotInfoParams(l_srvLotInfoRow);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR>
     * this.�T�[�r�X���I���s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4133090903BF
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams;
    }
    
    /**
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B<BR>
     * <BR>
     * �@@this.�T�[�r�X���I���s���R�s�[���āA�������e�̕ʃC���X�^���X��<BR>
     * �쐬����iclone�j�B<BR> 
     * �쐬�����R�s�[�����g��this.�T�[�r�X���I���s�ɃZ�b�g����B<BR>
     * @@roseuid 4133090A0006
     */
    public void createForUpdateParams() 
    {
        final String STR_METHOD_NAME = " createForUpdateParams() ";
        log.entering(STR_METHOD_NAME);

        SrvRegiLotInfoParams l_srvLotInfoParams = new SrvRegiLotInfoParams(this.srvLotInfoParams);
        this.srvLotInfoParams = l_srvLotInfoParams;
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createNew�ʔ�)<BR>
     * �T�[�r�X���I���̐V�K�ʔԂ�ԋp����B<BR>
     * (static���\�b�h)<BR>
     * <BR>
     * 1) �ȉ��̏����Łu�T�[�r�X���I���v�e�[�u������������B<BR>
     * [��������]<BR>
     * �@@�@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * [���я�]<BR>
     * �@@�@@�ʔԁ@@�~��<BR>
     * <BR>
     * 2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B<BR>
     * <BR>
     * 3) �������ʂ̌���>0���̏ꍇ�A�������ʂ̐擪�̗v�f�ƂȂ�<BR>
     * �@@�T�[�r�X���I���Params.get�ʔ�( )+1��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return long
     * @@roseuid 412F0F900362
     */
    public static long createNewConsecutiveNumbers(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewConsecutiveNumbers(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) �ȉ��̏����Łu�T�[�r�X���I���v�e�[�u������������B
            String l_strWhere = " institution_code = ? and srv_div = ? ";
                
            Object[] l_obj = {l_strInstitutionCode, l_strSrvDiv};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            List l_lisSrvLotInfoRowList = l_queryProcessor.doFindAllQuery(
                SrvRegiLotInfoRow.TYPE, 
                l_strWhere, 
                " consecutive_numbers desc ",
                "", 
                l_obj);//DataNetworkException, DataQueryException
                                  
            int l_intSrvLotInfoRowCnt = l_lisSrvLotInfoRowList.size();
            
            //2) �������ʂ̌���=0���̏ꍇ�A1��ԋp����B
            if (l_intSrvLotInfoRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            
            //3) �������ʂ̌���>0���̏ꍇ�A�������ʂ̐擪�̗v�f�ƂȂ�
            long l_lngConsecutiveNumbers = 
                ((SrvRegiLotInfoRow)l_lisSrvLotInfoRowList.get(0)).getConsecutiveNumbers()+1;
            log.exiting(STR_METHOD_NAME);
            
            return l_lngConsecutiveNumbers;
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�،���ЃR�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104884A0271
     */
    public String getInstitutionCode() 
    {
        final String STR_METHOD_NAME = " getInstitutionCode() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getInstitutionCode();
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�T�[�r�X�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 4104884A02EE
     */
    public String getSrvDiv() 
    {
        final String STR_METHOD_NAME = " String getSrvDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getSrvDiv();
    }
    
    /**
     * (get�ʔ�)<BR>
     * �ʔԂ�Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���p���ԗ����s.get�ʔ�()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 4122B6630152
     */
    public long getConsecutiveNumbers() 
    {
        final String STR_METHOD_NAME = " getConsecutiveNumbers() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getConsecutiveNumbers();
    }
    
    /**
     * (set�\�����ԁi���j)<BR>
     * �\�����ԁi���j�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�\�����ԁi���j()���R�[������B<BR>
     * [����]<BR>
     * �@@�\�����ԁi���j=����.�\�����ԁi���j<BR>
     * @@param l_tsAppliDateFrom - (�\�����ԁi���j)<BR>
     * @@roseuid 4133CF420074
     */
    public void setAppliDateFrom(Timestamp l_tsAppliDateFrom) 
    {
        final String STR_METHOD_NAME = " setAppliDateFrom(Timestamp) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAppliDateFrom(l_tsAppliDateFrom);
        
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * (get�\�����ԁi���j)<BR>
     * �\�����ԁi���j��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�\�����ԁi���j()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 4104884A034C
     */
    public Timestamp getAppliDateFrom() 
    {
        final String STR_METHOD_NAME = " getAppliDateFrom() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliDateFrom();
    }
    
    /**
     * (set�\�����ԁi���j)<BR>
     * �\�����ԁi���j�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�\�����ԁi���j()���R�[������B<BR>
     * [����]<BR>
     * �@@�\�����ԁi���j=����.�\�����ԁi���j<BR>
     * @@param l_tsAppliDateTo - (�\�����ԁi���j)<BR>
     * @@roseuid 4104884A037B
     */
    public void setAppliDateTo(Timestamp l_tsAppliDateTo) 
    {        
        final String STR_METHOD_NAME = " setAppliDateTo(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setAppliDateTo(l_tsAppliDateTo);
        log.exiting(STR_METHOD_NAME);
 
    }
    
    /**
     * (get�\�����ԁi���j)<BR>
     * �\�����ԁi���j��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�\�����ԁi���j()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 4104884A03C9
     */
    public Timestamp getAppliDateTo() 
    {
        final String STR_METHOD_NAME = " getAppliDateTo() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliDateTo();
    }
    
    /**
     * (set���I��)<BR>
     * ���I���̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set���I��()���R�[������B<BR>
     * [����]<BR>
     * �@@���I��=����.���I��<BR>
     * @@param l_tsLotDate - (���I��)<BR>
     * @@roseuid 41048C2401B6
     */
    public void setLotDate(Timestamp l_tsLotDate) 
    {
        final String STR_METHOD_NAME = " setLotDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setLotDate(l_tsLotDate);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���I��)<BR>
     * ���I����Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get���I��()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 41048C240271
     */
    public Timestamp getLotDate() 
    {
        final String STR_METHOD_NAME = " getLotDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams.getLotDate();
    }
    
    /**
     * (set�K�p�J�n��)<BR>
     * �K�p�J�n���̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�K�p�J�n��()���R�[������B<BR>
     * [����]<BR>
     * �@@�K�p�J�n��=����.�K�p�J�n��<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@roseuid 41048CAA0290
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAppliStartDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setAppliStartDate(l_tsAppliStartDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�K�p�J�n��)<BR>
     * �K�p�J�n����Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�K�p�J�n��()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 41048CAA02FE
     */
    public Timestamp getAppliStartDate() 
    {
        final String STR_METHOD_NAME = " getAppliStartDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getAppliStartDate();
    }
    
    /**
     * (set�K�p�I����)<BR>
     * �K�p�I�����̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�K�p�I����()���R�[������B<BR>
     * [����]<BR>
     * �@@�K�p�I����=����.�K�p�I����<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@roseuid 41048CFD0177
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAppliEndDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAppliEndDate(l_tsAppliEndDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�K�p�I����)<BR>
     * �K�p�I������Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�K�p�I����()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 41048CFD0223
     */
    public Timestamp getAppliEndDate() 
    {
        final String STR_METHOD_NAME = " getAppliEndDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        
        return this.srvLotInfoParams.getAppliEndDate();
    }
    
    /**
     * (set���p����)<BR>
     * ���p�����̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set���p����()���R�[������B<BR>
     * [����]<BR>
     * �@@���p����=����.���p����<BR>
     * @@param l_lngUseAmt - (���p����)<BR>
     * @@roseuid 4104884B00DB
     */
    public void setUseAmt(long l_lngUseAmt) 
    {
        final String STR_METHOD_NAME = " setUseAmt(long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setUseAmt(l_lngUseAmt);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (get���p����)<BR>
     * ���p������Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get���p����()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 4104884B0139
     */
    public long getUseAmt() 
    {
        final String STR_METHOD_NAME = " getUseAmt() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getUseAmt();
    }
    
    /**
     * (set���D�P��)<BR>
     * ���D�P�ʂ̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set���D�P��()���R�[������B<BR>
     * [����]<BR>
     * �@@���D�P��=����.���D�P��<BR>
     * @@param l_lngBiddingPrice - (���D�P��)<BR>
     * @@roseuid 41048DDA0213
     */
    public void setBiddingPrice(Long l_lngBiddingPrice) 
    {
        final String STR_METHOD_NAME = " setBiddingPrice(Long) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setBiddingPrice(l_lngBiddingPrice); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���D�P��)<BR>
     * ���D�P�ʂ�Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get���D�P��()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 41048DDA02B0
     */
    public Long getBiddingPrice() 
    {
        final String STR_METHOD_NAME = " getBiddingPrice() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.srvLotInfoParams.getBiddingPriceIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getBiddingPrice());
        }
    }
    
    /**
     * (set�o����)<BR>
     * �o�����̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�o����()���R�[������B<BR>
     * [����]<BR>
     * �@@�o����=����.�o����<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@roseuid 41048E2D03C9
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate) 
    {
        final String STR_METHOD_NAME = " setPaymentDate(Timestamp) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setPaymentDate(l_tsPaymentDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�o����)<BR>
     * �o������Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�o����()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 41048E2E001F
     */
    public Timestamp getPaymentDate() 
    {
        final String STR_METHOD_NAME = " getPaymentDate() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getPaymentDate();
    }
    
    /**
     * (set��W�g)<BR>
     * ��W�g�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set��W�g()���R�[������B<BR>
     * [����]<BR>
     * �@@��W�g=����.��W�g<BR>
     * @@param l_lngPublicOfferingQty - (��W�g)<BR>
     * @@roseuid 41048F1C01F4
     */
    public void setPublicOfferingQty(Long l_lngPublicOfferingQty) 
    {
        final String STR_METHOD_NAME = " setPublicOfferingQty(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setPublicOfferingQty(l_lngPublicOfferingQty); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get��W�g)<BR>
     * ��W�g��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get��W�g()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 41048F1C0252
     */
    public Long getPublicOfferingQty() 
    {
        final String STR_METHOD_NAME = " getPublicOfferingQty() ";
        log.entering(STR_METHOD_NAME);
        
        
        if (this.srvLotInfoParams.getPublicOfferingQtyIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getPublicOfferingQty());
        }
    }
    
    /**
     * (set�ō����D�z)<BR>
     * �ō����D�z�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�ō����D�z()���R�[������B<BR>
     * [����]<BR>
     * �@@�ō����D�z=����.�ō����D�z<BR>
     * @@param l_lngHighSuccessBid - (�ō����D�z)<BR>
     * @@roseuid 41048F6B02A0
     */
    public void setHighSuccessBid(Long l_lngHighSuccessBid) 
    {
        final String STR_METHOD_NAME = " setHighSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setHighSuccessBid(l_lngHighSuccessBid); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�ō����D�z)<BR>
     * �ō����D�z��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�ō����D�z()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 41048F6B02EE
     */
    public Long getHighSuccessBid() 
    {
        final String STR_METHOD_NAME = " getHighSuccessBid() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.srvLotInfoParams.getHighSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getHighSuccessBid());
        }
    }
    
    /**
     * (set�Œᗎ�D�z)<BR>
     * �Œᗎ�D�z�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�Œᗎ�D�z()���R�[������B<BR>
     * [����]<BR>
     * �@@�Œᗎ�D�z=����.�Œᗎ�D�z<BR>
     * @@param l_lngLowSuccesBid - (�Œᗎ�D�z)<BR>
     * @@roseuid 41048FC800FA
     */
    public void setLowSuccessBid(Long l_lngLowSuccesBid) 
    {
        final String STR_METHOD_NAME = "setLowSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setLowSuccessBid(l_lngLowSuccesBid); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�Œᗎ�D�z)<BR>
     * �Œᗎ�D�z��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�Œᗎ�D�z()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 41048FC80167
     */
    public Long getLowSuccessBid() 
    {
        final String STR_METHOD_NAME = " getLowSuccessBid() ";
        log.entering(STR_METHOD_NAME);

        if (this.srvLotInfoParams.getLowSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getLowSuccessBid());
        }
    }
    
    /**
     * (set���d���ϊz)<BR>
     * ���d���ϊz�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set���d���ϊz()���R�[������B<BR>
     * [����]<BR>
     * �@@���d���ϊz=����.���d���ϊz<BR>
     * @@param l_lngAverageSuccessBid - (���d���ϊz)<BR>
     * @@roseuid 4104900F0233
     */
    public void setAverageSuccessBid(Long l_lngAverageSuccessBid) 
    {
        final String STR_METHOD_NAME = " setAverageSuccessBid(Long) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvLotInfoParams.setAverageSuccessBid(l_lngAverageSuccessBid);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get���d���ϊz)<BR>
     * ���d���ϊz��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get���d���ϊz()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 4104900F02A0
     */
    public Long getAverageSuccessBid() 
    {
        final String STR_METHOD_NAME = " getAverageSuccessBid() ";
        log.entering(STR_METHOD_NAME);


        if (this.srvLotInfoParams.getAverageSuccessBidIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(this.srvLotInfoParams.getAverageSuccessBid());
        } 
    }
    
    /**
     * (set�^�p�敪)<BR>
     * �^�p�敪�̐ݒ���s���B<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s.set�^�p�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�^�p�敪=����.�^�p�敪<BR>
     * @@param l_strInvestDiv - (�^�p�敪)<BR>
     * @@roseuid 412AE771023D
     */
    public void setInvestDiv(String l_strInvestDiv) 
    {
        final String STR_METHOD_NAME = " setInvestDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.srvLotInfoParams.setInvestDiv(l_strInvestDiv); 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�^�p�敪)<BR>
     * �^�p�敪��Ԃ��B<BR>
     * <BR>
     * this.�T�[�r�X���I���s.get�^�p�敪()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 412AE7710318
     */
    public String getInvestDiv() 
    {
        final String STR_METHOD_NAME = " getInvestDiv() ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        return this.srvLotInfoParams.getInvestDiv();
    }
    
    /**
     * (is�I�[�N�V�����ݒ�)<BR>
     * �I�[�N�V�����̗L����ԋp����B<BR>
     * <BR>
     * 1) this.get�^�p�敪( )="�ʏ�^�p�i���I�L�I�[�N�V�����j"�̏ꍇ<BR>
     * �@@true��ԋp����B<BR>
     * <BR>
     * 2) ��L�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 412F020E0334
     */
    public boolean isAuctionSetting() 
    {
        final String STR_METHOD_NAME = " isAuctionSetting() ";
        log.entering(STR_METHOD_NAME);

        // 1) this.get�^�p�敪( )="�ʏ�^�p�i���I�L�I�[�N�V�����j"�̏ꍇtrue��ԋp����B
        if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(this.getInvestDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //2) ��L�ȊO�̏ꍇ�Afalse��ԋp����B
        return false;
    }
    
    /**
     * (_createNew�T�[�r�X���I���)<BR>
     * �V�K�ɃT�[�r�X���I���I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * 1) �T�[�r�X���I���Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * 2) �T�[�r�X���I���Params.set�،���ЃR�[�h()���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * 3) �T�[�r�X���I���Params.set�T�[�r�X�敪()���R�[������B<BR>
     * [����]<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 4) �T�[�r�X���I���Params.set�ʔ�()���R�[������B<BR>
     * [����]<BR>
     * �@@�ʔ�=�T�[�r�X���Ǘ�.createNew�ʔ�( )<BR>
     * �@@�@@[createNew�ʔԂɓn������]<BR>
     * �@@�@@�@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�T�[�r�X�敪=����.�T�[�r�X�敪<BR>
     * <BR>
     * 5) �T�[�r�X���I���̃R���X�g���N�^���R�[�����A��������<BR>
     * �@@�T�[�r�X���I���I�u�W�F�N�g��ԋp����B<BR>
     * [����]<BR>
     * �@@�T�[�r�X���I���Row=���������T�[�r�X���I���Params<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiServiceLotInfo
     * @@throws WEB3BaseException
     * @@roseuid 413E62C40197
     */
    public static WEB3SrvRegiServiceLotInfo createNewSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createNewSrvLotInfo(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X���I���Params�I�u�W�F�N�g�𐶐�����B
        SrvRegiLotInfoParams l_srvLotInfoParams = new SrvRegiLotInfoParams();
        
        //2) �T�[�r�X���I���Params.set�،���ЃR�[�h()���R�[������B
        l_srvLotInfoParams.setInstitutionCode(l_strInstitutionCode);
        
        //3) �T�[�r�X���I���Params.set�T�[�r�X�敪()���R�[������B
        l_srvLotInfoParams.setSrvDiv(l_strSrvDiv);
        
        //4) �T�[�r�X���I���Params.set�ʔ�()���R�[������B
        l_srvLotInfoParams.setConsecutiveNumbers(
            WEB3SrvRegiServiceLotInfo.createNewConsecutiveNumbers(
                l_strInstitutionCode, 
                l_strSrvDiv));
        
        //5) �T�[�r�X���I���̃R���X�g���N�^���R�[�����A���������T�[�r�X���I���I�u�W�F�N�g��ԋp����B
        WEB3SrvRegiServiceLotInfo l_srvLotInfo = 
            new  WEB3SrvRegiServiceLotInfo(l_srvLotInfoParams);
        
        log.exiting(STR_METHOD_NAME); 
        
        return l_srvLotInfo;
    }
    
    /**
     * (save�T�[�r�X���I���)<BR>
     * this.�T�[�r�X���I���s�I�u�W�F�N�g��<BR>
     * �����f�[�^�x�[�X�ɔ��f������B(Update)<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )��<BR>
     * �߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���I���s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���I���e�[�u�����X�V�iUpdate�j����B<BR>
     * @@roseuid 413E62C401A7
     */
    public void saveSrvLotInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveSrvLotInfo() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.�T�[�r�X���I���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
            this.srvLotInfoParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvLotInfoParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
        
            //2) this.�T�[�r�X���I���s�I�u�W�F�N�g�̓��e�ŁA
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doUpdateQuery(this.srvLotInfoParams);//DataNetworkException, DataQueryException
              
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
         
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (saveNew�T�[�r�X���I���)<BR>
     * this.�T�[�r�X���I���s�I�u�W�F�N�g�̏����f�[�^�x�[�X�ɔ��f������B(Insert)<BR>
     * <BR>
     * 1) this.�T�[�r�X���I���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B<BR>
     * �@@�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )<BR>
     * �@@�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * �@@�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l<BR>
     * <BR>
     * 2) this.�T�[�r�X���I���s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@�T�[�r�X���I���e�[�u�����X�V�iInsert�j����B<BR>
     * @@roseuid 413E62C401C6
     */
    public void saveNewSrvLotInfo() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewSrvLotInfo() ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) this.�T�[�r�X���I���s�I�u�W�F�N�g�Ɉȉ��̒l���Z�b�g����B
            this.srvLotInfoParams.setLastUpdater(
                WEB3Administrator.getInstanceFromLoginInfo().getAdministratorCode());
        
            this.srvLotInfoParams.setCreatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            this.srvLotInfoParams.setLastUpdatedTimestamp(
                GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //2) this.�T�[�r�X�}�X�^�[�s�I�u�W�F�N�g�̓��e�ŁA
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doInsertQuery(this.srvLotInfoParams);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
            
        log.exiting(STR_METHOD_NAME);               
    }
    
    /**
     * (remove�T�[�r�X���I���)<BR>
     * �T�[�r�X���I���̏����f�[�^�x�[�X����폜����B<BR>
     * <BR>
     * 1) �ȉ��������ɁA���Y���R�[�h���T�[�r�X���I���e�[�u�����폜����B<BR>
     * [�폜����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h and<BR>
     * �@@�T�[�r�X�敪=����.�T�[�r�X�敪 and<BR>
     * �@@�ʔ�=����.�ʔ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_lngConsecutiveNumbers - (�ʔ�)<BR>
     * @@roseuid 413E62C401E5
     */
    public void removeSrvLotInfo(String l_strInstitutionCode, String l_strSrvDiv, long l_lngConsecutiveNumbers) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " removeSrvLotInfo(String, String, long) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1) �ȉ��̏����Łu�T�[�r�X���I���v�e�[�u������������B
            String l_strWhere = " institution_code = ? and srv_div = ? and consecutive_numbers = ? ";
                
            Object[] l_obj = {l_strInstitutionCode, 
                    l_strSrvDiv, 
                    new Long(l_lngConsecutiveNumbers)};
                
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException, DataQueryException
            
            l_queryProcessor.doDeleteAllQuery(SrvRegiLotInfoRow.TYPE, l_strWhere, l_obj);//DataNetworkException, DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
            WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME, l_ex);
             
            log.exiting(STR_METHOD_NAME);
            
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3SrvRegiServiceLotInfo.class.getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@

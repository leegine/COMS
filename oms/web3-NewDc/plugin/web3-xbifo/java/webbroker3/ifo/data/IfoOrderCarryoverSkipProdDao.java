head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoOrderCarryoverSkipProdDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoOrderCarryoverSkipProdDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoOrderCarryoverSkipProdRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see IfoOrderCarryoverSkipProdPK 
 * @@see IfoOrderCarryoverSkipProdRow 
 */
public class IfoOrderCarryoverSkipProdDao extends DataAccessObject {


  /** 
   * ����{@@link IfoOrderCarryoverSkipProdDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoOrderCarryoverSkipProdRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoOrderCarryoverSkipProdRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoOrderCarryoverSkipProdDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoOrderCarryoverSkipProdDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoOrderCarryoverSkipProdRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoOrderCarryoverSkipProdRow )
                return new IfoOrderCarryoverSkipProdDao( (IfoOrderCarryoverSkipProdRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoOrderCarryoverSkipProdRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoOrderCarryoverSkipProdRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g 
    */
    protected IfoOrderCarryoverSkipProdDao( IfoOrderCarryoverSkipProdRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoOrderCarryoverSkipProdRow getIfoOrderCarryoverSkipProdRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g����{@@link IfoOrderCarryoverSkipProdDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoOrderCarryoverSkipProdRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoOrderCarryoverSkipProdDao}�擾�̂��߂Ɏw���{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoOrderCarryoverSkipProdDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoOrderCarryoverSkipProdDao forRow( IfoOrderCarryoverSkipProdRow row ) throws java.lang.IllegalArgumentException {
        return (IfoOrderCarryoverSkipProdDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoOrderCarryoverSkipProdRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoOrderCarryoverSkipProdRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoOrderCarryoverSkipProdPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoOrderCarryoverSkipProdParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoOrderCarryoverSkipProdRow.TYPE );
    }


  /** 
   * {@@link IfoOrderCarryoverSkipProdRow}����ӂɓ��肷��{@@link IfoOrderCarryoverSkipProdPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoOrderCarryoverSkipProdRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoOrderCarryoverSkipProdParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoOrderCarryoverSkipProdPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoOrderCarryoverSkipProdPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderCarryoverSkipProdRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderCarryoverSkipProdRow findRowByPk( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderCarryoverSkipProdPK pk = new IfoOrderCarryoverSkipProdPK( p_institutionCode, p_productCode, p_marketCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoOrderCarryoverSkipProdPK�I�u�W�F�N�g����{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoOrderCarryoverSkipProdPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoOrderCarryoverSkipProdRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoOrderCarryoverSkipProdRow findRowByPk( IfoOrderCarryoverSkipProdPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoOrderCarryoverSkipProdRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(IfoOrderCarryoverSkipProdRow)}���g�p���Ă��������B 
   */
    public static IfoOrderCarryoverSkipProdDao findDaoByPk( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderCarryoverSkipProdPK pk = new IfoOrderCarryoverSkipProdPK( p_institutionCode, p_productCode, p_marketCode );
        IfoOrderCarryoverSkipProdRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoOrderCarryoverSkipProdPK)}�����{@@link #forRow(IfoOrderCarryoverSkipProdRow)}���g�p���Ă��������B 
   */
    public static IfoOrderCarryoverSkipProdDao findDaoByPk( IfoOrderCarryoverSkipProdPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoOrderCarryoverSkipProdRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_productCode, p_marketCode, and �ɂĎw��̒l�����ӂ�{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productCode, p_marketCode, and �̒l�ƈ�v����{@@link IfoOrderCarryoverSkipProdRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoOrderCarryoverSkipProdRow findRowByInstitutionCodeProductCodeMarketCode( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoOrderCarryoverSkipProdRow.TYPE,
            "institution_code=? and product_code=? and market_code=?",
            null,
            new Object[] { p_institutionCode, p_productCode, p_marketCode } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoOrderCarryoverSkipProdRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoOrderCarryoverSkipProdDao.findRowsByInstitutionCodeProductCodeMarketCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductCodeMarketCode(String, String, String)}�����{@@link #forRow(IfoOrderCarryoverSkipProdRow)}���g�p���Ă��������B 
   */
    public static IfoOrderCarryoverSkipProdDao findDaoByInstitutionCodeProductCodeMarketCode( String p_institutionCode, String p_productCode, String p_marketCode ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductCodeMarketCode( p_institutionCode, p_productCode, p_marketCode ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
